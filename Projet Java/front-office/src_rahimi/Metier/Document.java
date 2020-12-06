package Metier;


public class Document {
	String path;
	Boolean isValide;
public Document(String pathString, Boolean isValideBoolean) {
	super();
	this.path = pathString;
	this.isValide = isValideBoolean;
}
public String getPath() {
	return path;
}
public void setPath(String pathString) {
	this.path = pathString;
}
public Boolean getIsValide() {
	return isValide;
}
public void setIsValide(Boolean isValideBoolean) {
	this.isValide = isValideBoolean;
}
@Override
public String toString() {
	return "Document [pathString=" + path + ", isValideBoolean=" + isValide + "]";
}

}
