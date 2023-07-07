package Frontend.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

import Backend.BusinessLayer.objects.Stock.Category;
import Backend.ServiceLayer.ServiceFactory;

public class CategoryModel1 {
    private String categoryname;
    private String fathername;
    public String getFatherName() {
    if (fathername == null) {
        return "No Father";
    }
    return fathername;
}

    private PropertyChangeSupport propertyChangeSupport;
    ServiceFactory service= ServiceFactory.getInstance();

    public CategoryModel1( String categoryname, String fathername) {
        this.categoryname = categoryname;
        this.fathername = fathername;
     //   propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public String getname() {
        return categoryname;
    }

    public void setcatName(String categoryname) {
        String oldID = this.categoryname;
        this.categoryname = categoryname;
        propertyChangeSupport.firePropertyChange("ID", oldID, categoryname);
    }

    public String getName() {
        return categoryname;
    }

    public void setfatherName(String fathername) {
        String oldName = this.fathername;
        this.fathername = fathername;
        propertyChangeSupport.firePropertyChange("Name", oldName, fathername);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public String getSubcategories(String id) {
LinkedList<String> out=new LinkedList<>();    
        for (Category category : ServiceFactory.getInstance().getCategoryService().categoryController.returnCategory(id).getSubCategories()) {
            out.addLast(category.getName());
    }
return out.toString();}
    
}
