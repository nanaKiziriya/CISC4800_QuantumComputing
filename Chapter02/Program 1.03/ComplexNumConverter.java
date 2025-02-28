import java.lang.Math;

public class ComplexNumConverter{
    // convert between general (cartesian), polar, and exponential (Euler's formula) form for complex numbers
    public static void main(String[]args){
        //driver
    }
}


// ComplexNumber object classes
abstract class ComplexNumber implements Cloneable{
    @Override
    abstract ComplexNumber clone();
    @Override
    abstract String toString();
}

class GenComplex extends ComplexNumber{
    double real=0, imaginary=0;

    GenComplex(){}
    GenComplex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public GenComplex clone(){
        return new GenComplex(real,imaginary);
    }
    public String toString(){
        return String.format("%f+%fi",real,imaginary);
    }
}

class PlrComplex extends ComplexNumber{
    double radius=0, piRadians=0;
    
    ExpComplex(){}
    ExpComplex(double radius, double radians){
        this.radius = radius;
        piRadians = radians/PI;
    }
    
    public String toString(){
        return String.format("%f,%fpi",radius,piRadians);
    }
}

class ExpComplex extends PlrComplex{
    ExpComplex(){
        super();
    }
    ExpComplex(double radius, double radians){
        super(radius, radians/PI);
    }
    
    public String toString(){
        return String.format("%fexp(%f)",radius,piRadians);
    }
}
