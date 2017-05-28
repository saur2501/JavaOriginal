package javaConcepts.self;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})	//Type means interface or class;// Default for Target is all Java Elements
@Retention(RetentionPolicy.RUNTIME)		//for @override it is source (compiler to see and report error);default is CLASS
public @interface AnnotationEg {
	public String name();
	public String phone() default "";	//optional to give it
	public String email() default "";
}
