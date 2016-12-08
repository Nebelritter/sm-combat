/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.window;

import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

/**
 * @author alters
 *
 */
public class UserListEditDialog extends BaseEditDialog {

    private static final long serialVersionUID = 1L;

    public UserListEditDialog(String caption) {
        super(caption);
    }

    @Override
    protected Component provideEditComponent() {
        //use grid

        return null;
    }

    @Override
    protected ClickListener provideSaveButtonListener() {
        //no further action
        return null;
    }

    @Override
    protected boolean isDirty() {
        return false;//always false, never dirty, because eidt is doner right away
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
