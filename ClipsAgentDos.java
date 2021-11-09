package test;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import net.sf.clipsrules.jni.*;

public class ClipsAgentDos extends Agent {

   Environment clips;

  protected void setup() {
      try {
           clips = new Environment();
      } catch (Exception e){}
     
    addBehaviour(new TellBehaviour());
    addBehaviour(new AskBehaviour());
  } 

  private class TellBehaviour extends Behaviour {

    boolean tellDone = false;

    public void action() {
        try{
        clips.eval("(clear)");
        clips.load("C:/jade/classe/clips/market/load-templates.clp");
        clips.load("C:/jade/classe/clips/market/load-facts.clp");
        clips.load("C:/jade/classe/clips/market/load-rules.clp");
        
        }catch (Exception e){}

        tellDone = true;
       
    } 
    
    public boolean done() {
      if (tellDone)
        return true;
      else
	return false;
    }
   

  }    // END of inner class ...Behaviour


  private class AskBehaviour extends Behaviour {

    boolean askDone = false;

    public void action() {
        try{
         
         clips.eval("(reset)");
         clips.eval("(facts)");
         clips.run();
        }catch(Exception e){}
       askDone = true;
        

    } 
    
    public boolean done() {
      if (askDone)
        return true;
      else
	return false;
    }
   
    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
