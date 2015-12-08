package storageData;

/**
 * Created by Agnieszka on 2015-11-28.
 */
public class Attribute {

    private String options;
    private int column;

    public Attribute(String options, int column){
        this.options = options;
        this.column = column;
    }

    public String getAttributeDescription(){
        return "";
    }
}
