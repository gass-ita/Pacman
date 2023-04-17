package Map;

import Entities.Entity;

public class Path extends Cell{
    Entity entity;

    public Path(){
        this.entity = null;
    }

    public void setEntity(Entity entity){
        this.entity = entity;
    }

    public Entity getEntity(){
        return this.entity;
    }

    @Override
    public String toString() {
        if(this.entity == null){
            return " ";
        }else{
            return this.entity.toString();
        }
    }


}
