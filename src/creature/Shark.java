package creature;

import java.awt.Color;

import ch.eiafr.gl.simulife.model.ICreature;

abstract class Shark implements ICreature {
    private int id;
    public Color color = Color.RED;
    int hunger=10;
    public  boolean isHunger() {
        return hunger==0;
    }

   
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    

}
