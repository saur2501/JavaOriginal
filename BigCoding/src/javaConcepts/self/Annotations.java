package javaConcepts.self;

import java.lang.annotation.Annotation;

@AnnotationEg(name = "vinod", email = "vinod@a.b")
public class Annotations {
	private String firstName, lastName;

	public Annotations(){}
	public Annotations(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static void main(String[] args) {
		Annotations annotations = new Annotations();
		Class cls = annotations.getClass();
		for (Annotation annotation: cls.getAnnotations()) {
			//System.out.println(annotation);
			if(annotation instanceof AnnotationEg){
				AnnotationEg annotationEg = (AnnotationEg) annotation;
				System.out.println("Name = " + annotationEg.name());
			}
		}
	}
}
