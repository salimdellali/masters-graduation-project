package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import vpcispanels.*;
import vpcisteamitemspanels.*;
import vpcisactoritemspanels.*;

public class PanelVpCIS extends JPanel {
	
	//public PanelVpCIS panelVpCIS ;
	public static CardLayout cl = new CardLayout();
	
	TestPanel tp = new TestPanel();
	
	//CIS
	public static AllCISProjectsPanel allCISProjectsPanel = new AllCISProjectsPanel();
	public static AddCISProjectPanel addCISProjectPanel = new AddCISProjectPanel();
	public static UpdateCISProjectPanel updateCISProjectPanel = new UpdateCISProjectPanel();
	public static DeleteCISProjectPanel deleteCISProjectPanel = new DeleteCISProjectPanel();
	
	//Viewpoint
	public static AllViewpointsPanel allViewpointsPanel = new AllViewpointsPanel();
	public static AddViewpointPanel addViewpointPanel = new AddViewpointPanel();
	public static UpdateViewpointPanel updateViewpointPanel = new UpdateViewpointPanel();
	public static DeleteViewpointPanel deleteViewpointPanel = new DeleteViewpointPanel();
	
	//Abstraction Level
	public static AllAbstractionLevelsPanel allAbstractionLevelsPanel = new AllAbstractionLevelsPanel();
	public static AddAbstractionLevelPanel addAbstractionLevelPanel = new AddAbstractionLevelPanel();
	public static UpdateAbstractionLevelPanel updateAbstractionLevelPanel = new UpdateAbstractionLevelPanel();
	public static DeleteAbstractionLevelPanel deleteAbstractionLevelPanel = new DeleteAbstractionLevelPanel();
	
	//Item
	public static AllItemsPanel allItemsPanel = new AllItemsPanel();
	
	//Team
	public static AllTeamsPanel allTeamsPanel = new AllTeamsPanel();
	public static AddTeamPanel addTeamPanel = new AddTeamPanel();
	public static EditTeamPanel editTeamPanel = new EditTeamPanel();
	
	//Activity Team
	public static AllActivityTeamsPanel allActivityTeamsPanel = new AllActivityTeamsPanel();
	public static AddActivityTeamPanel addActivityTeamPanel = new AddActivityTeamPanel();
	public static EditActivityTeamPanel editActivityTeamPanel = new EditActivityTeamPanel();
	
	//Information Team
	public static AllInformationTeamsPanel allInformationTeamsPanel = new AllInformationTeamsPanel();
	public static AddInformationTeamPanel addInformationTeamPanel = new AddInformationTeamPanel();
	public static EditInformationTeamPanel editInformationTeamPanel = new EditInformationTeamPanel();
	
	//Information Team Item
	public static AllInformationTeamItemsPanel allInformationTeamItemsPanel = new AllInformationTeamItemsPanel();
	
	//Data Team
	public static AllDataTeamsPanel allDataTeamsPanel = new AllDataTeamsPanel();
	public static AddDataTeamPanel addDataTeamPanel = new AddDataTeamPanel();
	public static EditDataTeamPanel editDataTeamPanel = new EditDataTeamPanel();
	
	//Knowledge Team
	public static AllKnowledgeTeamsPanel allKnowledgeTeamsPanel = new AllKnowledgeTeamsPanel();
	public static AddKnowledgeTeamPanel addKnowledgeTeamPanel = new AddKnowledgeTeamPanel();
	public static EditKnowledgeTeamPanel editKnowledgeTeamPanel = new EditKnowledgeTeamPanel();
	
	//Non Funtional Requirement Team
	public static AllNonFunctionalRequirementTeamsPanel allNonFunctionalRequirementTeamsPanel = new AllNonFunctionalRequirementTeamsPanel();
	public static AddNonFunctionalRequirementTeamPanel addNonFunctionalRequirementTeamPanel = new AddNonFunctionalRequirementTeamPanel();
	public static EditNonFunctionalRequirementTeamPanel editNonFunctionalRequirementTeamPanel = new EditNonFunctionalRequirementTeamPanel();
	
	//Actor
	public static AllActorsPanel allActorsPanel = new AllActorsPanel();
	public static AddActorPanel addActorPanel = new AddActorPanel();
	public static EditActorPanel editActorPanel = new EditActorPanel();
	
	//Activity Actor
	
	
	//Information Actor
	
	
	//Information Actor Item
	
	
	//Data Actor
	
	
	//Knowledge Actor
	
	
	//Non Functional Requirement Actor
	
	
	
	public static ModifyActorPanel modifyActorPanel = new ModifyActorPanel();
	public PanelVpCIS(){
		
		setBorder(BorderFactory.createLineBorder(Color.RED,3));
		setLayout(cl);
		add(tp,"TestPanel");
		//CIS
		add(allCISProjectsPanel,"AllCISProjectsPanel");
		add(addCISProjectPanel,"AddCISProjectPanel");
		add(updateCISProjectPanel,"UpdateCISProjectPanel");
		add(deleteCISProjectPanel,"DeleteCISProjectPanel");
		
		//Viewpoint
		add(allViewpointsPanel,"AllViewpointsPanel");
		add(addViewpointPanel,"AddViewpointPanel");
		add(updateViewpointPanel,"UpdateViewpointPanel");
		add(deleteViewpointPanel,"DeleteViewpointPanel");
		
		//Abstraction Level
		add(allAbstractionLevelsPanel,"AllAbstractionLevelsPanel");
		add(addAbstractionLevelPanel,"AddAbstractionLevelPanel");
		add(updateAbstractionLevelPanel,"UpdateAbstractionLevelPanel");
		add(deleteAbstractionLevelPanel,"DeleteAbstractionLevelPanel");
		
		//Item
		add(allItemsPanel,"AllItemsPanel");
		
		//Team
		add(allTeamsPanel,"AllTeamsPanel");
		add(addTeamPanel,"AddTeamPanel");
		add(editTeamPanel,"EditTeamPanel");
		
		//Activity Team
		add(allActivityTeamsPanel,"AllActivityTeamsPanel");
		add(addActivityTeamPanel,"AddActivityTeamPanel");
		add(editActivityTeamPanel,"EditActivityTeamPanel");
		
		//Information Team
		add(allInformationTeamsPanel,"AllInformationTeamsPanel");
		add(addInformationTeamPanel,"AddInformationTeamPanel");
		add(editInformationTeamPanel,"EditInformationTeamPanel");
		
		//Information Team Item
		add(allInformationTeamItemsPanel,"AllInformationTeamItemsPanel");
		
		//Data Team
		add(allDataTeamsPanel,"AllDataTeamsPanel");
		add(addDataTeamPanel,"AddDataTeamPanel");
		add(editDataTeamPanel,"EditDataTeamPanel");
		
		//Knowledge Team
		add(allKnowledgeTeamsPanel,"AllKnowledgeTeamsPanel");
		add(addKnowledgeTeamPanel,"AddKnowledgeTeamPanel");
		add(editKnowledgeTeamPanel,"EditKnowledgeTeamPanel");
		
		//Non Funtional Requirement Team
		add(allNonFunctionalRequirementTeamsPanel,"AllNonFunctionalRequirementTeamPanel");
		add(addNonFunctionalRequirementTeamPanel,"AddNonFunctionalRequirementTeamPanel");
		add(editNonFunctionalRequirementTeamPanel,"EditNonFunctionalRequirementTeamPanel");
		
		//Actor
		add(allActorsPanel,"AllActorsPanel");
		add(addActorPanel,"AddActorPanel");
		add(editActorPanel,"EditActorPanel");
		
		//Activity Actor
		
		
		//Information Actor
		
		
		//Information Actor Item
		
		
		//Data Actor
		
		
		//Knowledge Actor
		
		
		//Non Functional Requirement Actor
		
		
		
		
		
		add(modifyActorPanel,"ModifyActorPanel");
		
		//add(tempoClassAddCIS,"TempoClassAddCIS");
		cl.show(this, "AllCISProjectsPanel");
		//cl.show(this, "ModifyActorPanel");
		//showPanel("AllCISProjectsPanel");
	}
	/*
	public void showPanel(String namePanel){
		//this.revalidate();
		panelVpCIS = new PanelVpCIS();
		panelVpCIS.cl.show(this, namePanel);
		//cl.show(this, namePanel);
		
	}
	*/
	
}
