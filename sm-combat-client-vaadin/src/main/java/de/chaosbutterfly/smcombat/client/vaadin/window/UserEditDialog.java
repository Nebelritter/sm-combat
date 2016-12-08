/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.window;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.chaosbutterfly.smcombat.client.vaadin.session.SMVaadinSession;
import de.chaosbutterfly.smcombat.client.vaadin.validators.PasswordValidator;
import de.chaosbutterfly.smcombat.client.vaadin.view.ViewConstants;
import de.chaosbutterfly.smcombat.model.session.UserSession;
import de.chaosbutterfly.smcombat.model.user.KnownUser;

/**
 * @author alters
 *
 */
public class UserEditDialog extends BaseEditDialog {
    private static final long serialVersionUID = 1L;

    private KnownUser editUser;

    private TextField usernameTF;
    private TextField passwordTF;
    private CheckBox isAdminCB;

    public UserEditDialog(String caption) {
        super(caption);
    }

    public KnownUser getKnownUserData() {
        return editUser;
    }

    public void setKnownUserData(KnownUser user) {
        this.editUser = user;
        if (user != null) {
            usernameTF.setValue(user.getUserName());
            passwordTF.setValue(user.getPassword());
            if (isAdminCB != null) {
                isAdminCB.setValue(user.getIsAdmin());
            }
        }
    }

    @Override
    protected ClickListener provideSaveButtonListener() {
        return new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {

                if (editUser == null) {
                    editUser = new KnownUser();
                }
                editUser.setUserName(usernameTF.getValue());
                editUser.setPassword(passwordTF.getValue());
                if (isAdminCB != null) {
                    editUser.setIsAdmin(isAdminCB.getValue());
                }
                close();
            }
        };
    }

    @Override
    protected Component provideEditComponent() {
        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);

        usernameTF = new TextField("Username:");
        usernameTF.setNullRepresentation("");
        content.addComponent(usernameTF);

        passwordTF = new TextField("Password:");
        passwordTF.setNullRepresentation("");
        passwordTF.addValidator(new PasswordValidator());
        content.addComponent(passwordTF);

        //only display admin checkbox if you may give admin rights
        UI ui = UI.getCurrent();
        VaadinSession vaadinSession = ui.getSession();
        Object attribute = vaadinSession.getAttribute(ViewConstants.ATTRIBUTE_NAME_SM_VAADIN_SESSION);
        SMVaadinSession session = (SMVaadinSession) attribute;
        UserSession userSession = session.getUserSession();
        if (Boolean.TRUE.equals(userSession.getIsAdmin())) {
            isAdminCB = new CheckBox("is Admin?");
            content.addComponent(isAdminCB);
        }

        return content;
    }

    @Override
    protected boolean isDirty() {
        if (editUser == null)
            return false;
        boolean dirty = false;
        dirty = stringDirty(editUser.getUserName(), usernameTF.getValue()) || dirty;
        dirty = stringDirty(editUser.getPassword(), passwordTF.getValue()) || dirty;
        if (isAdminCB != null) {
            dirty = booleanDirty(editUser.getIsAdmin(), isAdminCB.getValue()) || dirty;
        }
        return dirty;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
