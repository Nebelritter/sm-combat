package de.chaosbutterfly.smcombat.client.vaadin;

import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import de.chaosbutterfly.smcombat.client.vaadin.view.LoginView;
import de.chaosbutterfly.smcombat.client.vaadin.view.ViewConstants;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@CDIUI("")
public class SMClientVaadinUI extends UI {

    /**  */
    private static final long serialVersionUID = 1L;

	private transient CDIViewProvider viewProvider;

    @Inject
    public SMClientVaadinUI(CDIViewProvider viewProvider) {
        super();
        this.viewProvider = viewProvider;
    }

    @Override
    protected void init(VaadinRequest request) {

        Navigator navigator = new Navigator(this, this);

        navigator.addProvider(viewProvider);
        navigator.navigateTo(LoginView.NAME);
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        getNavigator().addViewChangeListener(new LogInViewChangeListener());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private class LogInViewChangeListener implements ViewChangeListener {
        /***/
        private static final long serialVersionUID = 1L;

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {

            // Check if a user has logged in
            boolean isLoggedIn = getSession().getAttribute(ViewConstants.ATTRIBUTE_NAME_SM_VAADIN_SESSION) != null;
            boolean isLoginView = event.getNewView() instanceof LoginView;

            if (!isLoggedIn && !isLoginView) {
                // Redirect to login view always if a user has not yet
                // logged in
                getNavigator().navigateTo(LoginView.NAME);
                return false;

            } else if (isLoggedIn && isLoginView) {
                // If someone tries to access to login view while logged in,
                // then cancel
                return false;
            }

            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {

        }
    }
}
