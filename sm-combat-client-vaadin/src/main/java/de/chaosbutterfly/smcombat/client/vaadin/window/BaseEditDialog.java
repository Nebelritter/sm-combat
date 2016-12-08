/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.window;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author alters
 *
 */
public abstract class BaseEditDialog extends Window {

    private static final long serialVersionUID = 1L;

    public static final int RESULT_NOT_SET = -1;
    public static final int RESULT_CANCEL = 0;
    public static final int RESULT_SAVE = 1;

    private int result = RESULT_NOT_SET;

    protected Button cancelButton;
    protected Button saveButton;

    public BaseEditDialog(String caption) {
        VerticalLayout content = new VerticalLayout();
        setContent(content);

        // Center it in the browser window
        center();

        content.addComponent(provideEditComponent());

        HorizontalLayout buttonsHLO = new HorizontalLayout();
        buttonsHLO.setMargin(true);

        cancelButton = new Button("Cancel");
        cancelButton.addClickListener(new CancelButtonClicklistener());
        saveButton = new Button("Save");
        saveButton.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                result = RESULT_SAVE;
            }
        });
        ClickListener saveButtonListener = provideSaveButtonListener();
        if (saveButtonListener != null) {
            saveButton.addClickListener(saveButtonListener);
        }

        buttonsHLO.addComponent(cancelButton);
        buttonsHLO.addComponent(saveButton);

        content.addComponent(buttonsHLO);

        setClosable(false);//not closable because they shall use them buttons!
    }

    protected abstract Component provideEditComponent();

    protected ClickListener provideSaveButtonListener() {
        return null;
    }

    protected boolean isDirty() {
        return false;
    }

    protected boolean stringDirty(String original, String changed) {
        if (original != null && changed != null) {
            return original.equals(changed);
        } else
            return !(original == null && changed == null);
    }

    protected boolean booleanDirty(Boolean original, Boolean changed) {
        if (original != null && changed != null) {
            return original.equals(changed);
        } else
            return !(original == null && changed == null);
    }

    /**
     * @return the result
     */
    public int getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private class CancelButtonClicklistener implements ClickListener {

        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            if (isDirty()) {
                // quick confirmation saying unsaved will be lost
                ConfirmDialog.show(UI.getCurrent(), "Are you sure?", new ConfirmDialog.Listener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClose(ConfirmDialog dialog) {
                        if (dialog.isConfirmed()) {
                            // Confirmed loss of unchanged changes
                            result = RESULT_CANCEL;
                            close();
                        } else {
                            // User did not confirm loss of unsaved changes                                                            
                        }
                    }
                });
            }
        }
    }

}
