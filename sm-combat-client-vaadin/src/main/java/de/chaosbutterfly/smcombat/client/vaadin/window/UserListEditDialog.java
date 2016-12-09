/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.window;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickListener;

import de.chaosbutterfly.smcombat.core.session.service.KnownUserAdminService;
import de.chaosbutterfly.smcombat.model.user.KnownUser;

import com.vaadin.ui.renderers.ImageRenderer;

/**
 * @author alters
 *
 */
public class UserListEditDialog extends BaseEditDialog {

    private static final long serialVersionUID = 1L;
    private Grid grid;

    private transient KnownUserAdminService userAdminService;

    public UserListEditDialog(String caption, KnownUserAdminService userAdminService) {
        super(caption);
        this.userAdminService = userAdminService;
        updateData();
    }

    @Override
    protected Component provideEditComponent() {
        //use grid
        grid = new Grid();
		grid.setSizeFull();
		grid.setEditorEnabled(true);
		grid.setSelectionMode(SelectionMode.NONE);

		grid.addColumn("Name", String.class).setExpandRatio(2);
		grid.addColumn("Password", String.class).setExpandRatio(2);
        grid.addColumn("isAdmin", Boolean.class).setExpandRatio(1);

        RendererClickListener listener = new RendererClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void click(RendererClickEvent e) {
                Object item = e.getItemId();
                Notification.show("Deleted item " + item);
            }
        };

        grid.addColumn("delete", String.class).setRenderer(new ButtonRenderer(listener)).setExpandRatio(1)
                .setEditable(false);

        grid.getEditorFieldGroup().addCommitHandler(new CommitHandler() {
            private static final long serialVersionUID = 1L;

            @Override
            public void preCommit(CommitEvent commitEvent) throws CommitException {
                // Do nothing
                Notification.show("perCommit saved");
            }

            @Override
            public void postCommit(CommitEvent commitEvent) throws CommitException {
                Collection<Field<?>> fields = commitEvent.getFieldBinder().getFields();
                for (Field<?> field : fields) {
                    Object value = field.getValue();
                }
                Item item = commitEvent.getFieldBinder().getItemDataSource();
                Collection<?> ids = item.getItemPropertyIds();

                Notification.show("Changes saved");
            }
        });
		return grid;
    }


    private void updateData() {

        List<KnownUser> knownUsers = userAdminService.getAllKnownUsers();
        for (KnownUser knownUser : knownUsers) {
            grid.addRow(
            /* name */knownUser.getUserName(),
            /* password */knownUser.getPassword(),
            /* isAdmin */knownUser.getIsAdmin(),
            /* delete */"X");
        }

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


}
