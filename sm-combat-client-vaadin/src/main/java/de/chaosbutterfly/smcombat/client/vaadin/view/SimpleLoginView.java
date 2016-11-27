/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import de.chaosbutterfly.smcombat.client.vaadin.session.SMVaadinSession;
import de.chaosbutterfly.smcombat.client.vaadin.session.VaadinSessionManager;
import de.chaosbutterfly.smcombat.client.vaadin.validators.PasswordValidator;
import de.chaosbutterfly.smcombat.core.session.data.UserLogInData;

/**
 * @author Alti
 *
 */
@CDIView("LoginView")
public class SimpleLoginView extends CustomComponent implements View {

    /**     */
    private static final long serialVersionUID = 1L;

    public static final String NAME = "LoginView";

    private VaadinSessionManager sessionManager;

    //UI components
    private TextField user;
    private PasswordField password;
    private Button loginButton;

    @Inject
    public SimpleLoginView(VaadinSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @PostConstruct
    public void buildView() {
        setSizeFull();

        // Create the user input field
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("Your username");

        // Create the password input field
        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");
//        password.addValidator(new PasswordValidator());//temporary switched off for better testing

        // Create login button
        loginButton = new Button("Login", new LogInClickListener());

        // Add both to a panel
        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application. For test use 'Test/passw0rd'");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        // The view root layout
        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // focus the username field when user arrives to the login view
        user.focus();
    }

    private class LogInClickListener implements Button.ClickListener {
        /**      */
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            // Validate the fields using the navigator. By using validators for the
            // fields we reduce the amount of queries we have to use to the database
            // for wrongly entered passwords//
            if (!user.isValid() || !password.isValid()) {
                return;
            }
            String userName = SimpleLoginView.this.user.getValue();
            String password = SimpleLoginView.this.password.getValue();
            UserLogInData logInData = new UserLogInData();
            logInData.setUserName(userName);
            logInData.setPassword(password);
            // Validate username and password with database here
            SMVaadinSession session = sessionManager.logIn(logInData);
            if (session != null) {
                // Store the current user in the service session
                getSession().setAttribute("user", userName);
                // Navigate to main view
                getUI().getNavigator().navigateTo(SimpleLoginMainView.NAME);
            } else {
                //TODO display message log in was wrong
                // Wrong password clear the password field and re-focuses it
                SimpleLoginView.this.password.setValue(null);
                SimpleLoginView.this.password.focus();
            }
        }
    }

}
