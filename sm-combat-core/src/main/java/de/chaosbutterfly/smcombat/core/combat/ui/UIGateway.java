package de.chaosbutterfly.smcombat.core.combat.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.chaosbutterfly.smcombat.core.combat.commands.CharacterActsCommand;
import de.chaosbutterfly.smcombat.core.combat.controller.CombatLogicController;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;
import de.chaosbutterfly.smcombat.model.combat.CombatCharaterRepository;

/**
 * 
 * should call UI and provide data
 * 
 * 
 * so to say a service providing data for UI, Facade pattern, somehow
 * 
 * @author Alti
 *
 */
public class UIGateway {

    private CombatLogicController combatLogicController;
    private CombatCharaterRepository characterRepo;

    private UIClientImplementation uiClient;

    public UIGateway() {
        super();
    }

    public UIGateway(CombatLogicController combatLogicController, CombatCharaterRepository characterRepo) {
        super();
        this.combatLogicController = combatLogicController;
        this.characterRepo = characterRepo;
    }

    public CharacterActsCommand pullCharacterActCommand(CombatCharacterSM character) {
        if (uiClient != null) {
            return uiClient.pullCharacterActCommand(character);
        } else {
            throw new RuntimeException("No UI client implementation registered");
        }
    }

    public List<CombatCharacterSM> pushCharacterActions() {
        return characterRepo.findAllOrderedByTick();
    }

    public UIClientImplementation getUiClient() {
        return uiClient;
    }

    public void setUiClient(UIClientImplementation uiClient) {
        this.uiClient = uiClient;
    }
}
