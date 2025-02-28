public class ComplexNumConverter{
    // convert between general (cartesian), polar, and exponential (Euler's formula) form for complex numbers
    public static void main(String[]args){
        //driver
    }
}


// ComplexNumber object classes
abstract class ComplexNumber implements Cloneable{
    abstract public ComplexNumber clone();
    abstract public boolean equals();
    abstract public ExpComplex toExpComplex();
    abstract public GenComplex toGenComplex();
    abstract public PlrComplex toPlrComplex();
    abstract public String toString();
}

class GenComplex extends ComplexNumber{
    // datafields
    private double real=0, imaginary=0;
    // constructors
    GenComplex(){}
    GenComplex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }
    // methods
    public GenComplex clone(){
        return new GenComplex(real,imaginary);
    }
    public boolean equals(ComplexNumber c){
        return (real==c.getReal() && imaginary==c.getImaginary()) || ();
    }
    public double getRadius(){ return Math.sqrt(Math.pow(real,2)+Math.pow(imaginary,2)); } //
    public double getRadians(){ return Math.atan2(imaginary,real); } //
    public double getPiRadians(){ return getRadians()/Math.PI; } //
    public double getReal(){ return real; } //
    public double getImaginary(){ return imaginary; } //
    public ExpComplex toExpComplex(){ return new ExpComplex(getRadius(),getRadians()); } //
    public GenComplex toGenComplex(){ return clone(); } //
    public PlrComplex toPlrComplex(){ return new PlrComplex(getRadius(),getRadians()); } //
    public String toString(){ return String.format("%f+%fi",real,imaginary); } //
}

class PlrComplex extends ComplexNumber{
    // datafiels
    private double radius=0, piRadians=0;
    // constructors
    PlrComplex(){}
    PlrComplex(double radius, double radians){
        this.radius = radius;
        piRadians = radians/Math.PI;
    }
    // methods
    public PlrComplex
    public String toString(){
        return String.format("%f,%fpi",radius,piRadians);
    }
}

class ExpComplex extends PlrComplex{
    // datafields inherited from superclass
    // constructors
    ExpComplex(){
        super();
    }
    ExpComplex(double radius, double radians){
        super(radius, radians);
    }
    // methods
    public String toString(){
        return String.format("%fexp(%f)",radius,piRadians);
    }
}
