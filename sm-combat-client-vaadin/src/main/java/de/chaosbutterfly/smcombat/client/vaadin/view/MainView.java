/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.view;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

import de.chaosbutterfly.smcombat.client.vaadin.session.SMVaadinSession;
import de.chaosbutterfly.smcombat.client.vaadin.session.VaadinSessionManager;
import de.chaosbutterfly.smcombat.client.vaadin.window.UserEditDialog;
import de.chaosbutterfly.smcombat.core.session.service.KnownUserAdminService;
import de.chaosbutterfly.smcombat.model.session.UserSession;
import de.chaosbutterfly.smcombat.model.user.KnownUser;

/**
 * @author Alti
 *
 */
@CDIView("Main")
public class MainView extends CustomComponent implements View {
    private static final Logger LOGGER = Logger.getLogger(MainView.class.getName());
    /**    */
    private static final long serialVersionUID = 1L;

    public static final String NAME = "Main";

    private static final String UI_TXT_MENU_ADMIN = "Admin";
    private static final String UI_TXT_MENU_ADMIN_ADD_USER = "Add User";
    private static final String UI_TXT_MENU_ADMIN_EDIT_USERS = "Edit Users";

    private static final String UI_TXT_MENU_USER = "User";
    private static final String UI_TXT_MENU_USER_PROFILE = "Profile";
    private static final String UI_TXT_MENU_USER_LOGOUT = "Logout";

    private VaadinSessionManager sessionManager;
    private KnownUserAdminService userAdminService;

    //========= UI Components =============
    private MenuBar menuBar;

    private Label userNameLB = new Label();

    //============ Data ===================

    private KnownUser selectedUser;

    @Inject
    public MainView(VaadinSessionManager sessionManager, KnownUserAdminService userAdminService) {
        this.sessionManager = sessionManager;
        this.userAdminService = userAdminService;
    }

    @PostConstruct
    public void buildView() {
        GridLayout mainGrid = new GridLayout(1, 1);
        //build menu bar
        menuBar = new MenuBar();
        createUserMenu();
        createAdminMenu();
        mainGrid.addComponent(menuBar);

        mainGrid.addComponent(userNameLB);
        //build table with characters

        //build list with active sessions
        setCompositionRoot(mainGrid);

    }

    private void createAdminMenu() {
        MenuItem menuUser = menuBar.addItem(UI_TXT_MENU_ADMIN, null, null);
        menuUser.addItem(UI_TXT_MENU_ADMIN_ADD_USER, provideAddUserCommand());
        menuUser.addItem(UI_TXT_MENU_ADMIN_EDIT_USERS, provideEditUsersCommand());
    }

    private void createUserMenu() {
        MenuItem menuUser = menuBar.addItem(UI_TXT_MENU_USER, null, null);
        menuUser.addItem(UI_TXT_MENU_USER_PROFILE, provideUserEditCommand(new SessionKnownUserProvider()));
        menuUser.addSeparator();
        menuUser.addItem(UI_TXT_MENU_USER_LOGOUT, provideLogOutCommand());
    }

    @Override
    public void enter(ViewChangeEvent event) {
        SMVaadinSession session = getSmVaadinSession();
        UserSession userSession = session.getUserSession();
        if (Boolean.TRUE.equals(userSession.getIsAdmin())) {
            setAdminMenuVisible(true);
        } else {
            setAdminMenuVisible(false);
        }
        // Get the user name from the session       
        String username = userSession.getUserName();
        // And show the username
        userNameLB.setValue("Hello " + username);
    }

    private void setAdminMenuVisible(boolean visible) {
        List<MenuItem> mainItems = menuBar.getItems();
        for (MenuItem menuItem : mainItems) {
            if (UI_TXT_MENU_ADMIN.equals(menuItem.getText())) {
                menuItem.setVisible(visible);
            }
        }
    }

    private SMVaadinSession getSmVaadinSession() {
        Object attribute = getSession().getAttribute(ViewConstants.ATTRIBUTE_NAME_SM_VAADIN_SESSION);
        return (SMVaadinSession) attribute;
    }

    private Command provideLogOutCommand() {
        Command logoutCommand = new Command() {
            /**       */
            private static final long serialVersionUID = 1L;

            @Override
            public void menuSelected(MenuItem selectedItem) {
                // "Logout" the user               
                SMVaadinSession sessionToEnd = getSmVaadinSession();
                if (sessionManager.logOut(sessionToEnd)) {
                    getSession().setAttribute(ViewConstants.ATTRIBUTE_NAME_SM_VAADIN_SESSION, null);
                }
                //remove/close all open windows
                UI ui = getUI();
                Collection<Window> windows = ui.getWindows();
                for (Window window : windows) {
                    ui.removeWindow(window);
                }
                // Refresh this view, should redirect to login view
                getUI().getNavigator().navigateTo(LoginView.NAME);
                Notification.show("Logout completed", Type.TRAY_NOTIFICATION);
            }
        };
        return logoutCommand;
    }

    private Command provideUserEditCommand(IGetKnownUserProvider provider) {
        //open user edit dialog at first
        Command editUserCommand = new Command() {
            private static final long serialVersionUID = 1L;

            IGetKnownUserProvider knownUserProvider = provider;

            @Override
            public void menuSelected(MenuItem selectedItem) {
                KnownUser user = knownUserProvider.provideKnownUser();
                //open user edit dialog
                //create dialog
                UserEditDialog myUserEditWindow = new UserEditDialog("Edit user: " + user);
                myUserEditWindow.setKnownUserData(cloneUser(user));
                //close listener
                myUserEditWindow.addCloseListener(new CloseListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void windowClose(CloseEvent e) {
                        UserEditDialog editWindow = (UserEditDialog) e.getWindow();
                        int result = editWindow.getResult();
                        switch (result) {
                        case UserEditDialog.RESULT_SAVE:
                            //get new data and save 
                            KnownUser editedUser = editWindow.getKnownUserData();
                            LOGGER.finest("User edited: old(" + user + "),new:" + editedUser);
                            userAdminService.editUser(user.getId(), editedUser);
                            Notification.show("User changed", Type.TRAY_NOTIFICATION);
                            break;
                        default:
                            Notification.show("User edit cancelled", Type.TRAY_NOTIFICATION);
                            LOGGER.finest("UserEdit cancelled");
                            break;
                        }
                    }
                });
                //open
                UI.getCurrent().addWindow(myUserEditWindow);
            }
        };
        return editUserCommand;
    }

    private KnownUser cloneUser(KnownUser selectedUser) {
        // TODO Auto-generated method stub
        return selectedUser;
    }

    private Command provideAddUserCommand() {
        Command addUserCommand = new Command() {
            private static final long serialVersionUID = 1L;

            @Override
            public void menuSelected(MenuItem selectedItem) {
                //open user edit dialog
                //create dialog
                UserEditDialog addUserWindow = new UserEditDialog("Add user");
                addUserWindow.setKnownUserData(new KnownUser());
                //close listener
                addUserWindow.addCloseListener(new CloseListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void windowClose(CloseEvent e) {
                        UserEditDialog editWindow = (UserEditDialog) e.getWindow();
                        int result = editWindow.getResult();
                        switch (result) {
                        case UserEditDialog.RESULT_SAVE:
                            //get new data and save 
                            KnownUser editedUser = editWindow.getKnownUserData();
                            LOGGER.finest("User added:" + editedUser);
                            userAdminService.addUser(editedUser.getUserName(), editedUser.getPassword(),
                                    editedUser.getIsAdmin());
                            Notification.show("User added", Type.TRAY_NOTIFICATION);
                            break;
                        default:
                            LOGGER.finest("User adding cancelled");
                            Notification.show("User adding cancelled", Type.TRAY_NOTIFICATION);
                            break;
                        }
                    }
                });
                //open
                UI.getCurrent().addWindow(addUserWindow);
            }
        };
        return addUserCommand;
    }

    private Command provideEditUsersCommand() {
        Command editUsersCommand = new Command() {
            /**       */
            private static final long serialVersionUID = 1L;

            @Override
            public void menuSelected(MenuItem selectedItem) {
                //open user find dialog
                //table with delete/edit possibility
                //open user edit dialog
                provideUserEditCommand(new SelectedKnownUserProvider());
            }
        };
        return editUsersCommand;
    }

    private interface IGetKnownUserProvider extends Serializable {
        KnownUser provideKnownUser();
    }

    private class SessionKnownUserProvider implements IGetKnownUserProvider {
        private static final long serialVersionUID = 1L;

        @Override
        public KnownUser provideKnownUser() {
            SMVaadinSession smVaadinSession = getSmVaadinSession();
            String userName = smVaadinSession.getUserSession().getUserName();
            return userAdminService.getUser(userName);
        }
    }

    private class SelectedKnownUserProvider implements IGetKnownUserProvider {
        private static final long serialVersionUID = 1L;

        @Override
        public KnownUser provideKnownUser() {
            return selectedUser;
        }
    }
}
