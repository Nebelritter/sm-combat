/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

import de.chaosbutterfly.smcombat.client.vaadin.session.VaadinSessionManager;

/**
 * @author Alti
 *
 */
@CDIView("Main")
public class SimpleLoginMainView extends CustomComponent implements View {

    /**    */
    private static final long serialVersionUID = 1L;

    public static final String NAME = "Main";

    Label text = new Label();

    Button logout = new Button("Logout", new Button.ClickListener() {

        /**       */
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {

            // "Logout" the user
            getSession().setAttribute("user", null);

            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(NAME);
        }
    });

    private VaadinSessionManager sessionManager;

    @Inject
    public SimpleLoginMainView(VaadinSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @PostConstruct
    public void buildView() {
        setCompositionRoot(new CssLayout(text, logout));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));
        // And show the username
        text.setValue("Hello " + username);
    }
}
