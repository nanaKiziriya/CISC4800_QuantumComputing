public class ComplexNumConverter{
    // convert between general (cartesian), polar, and exponential (Euler's formula) form for complex numbers
    public static void main(String[]args){
        //driver
    }
}


// ComplexNumber object classes
abstract class ComplexNumber implements Cloneable{
    
    public ComplexNumber clone() throws CloneNotSupportedException{ return (ComplexNumber)super.clone(); }
    public boolean equals(ComplexNumber that){
        return (this.getReal()==that.getReal() && this.getImaginary()==this.getImaginary())
            || (this.getPiRadians()==that.getPiRadians() && this.getRadius()==that.getRadius());
    }
    
    abstract public double getRadius();
    abstract public double getRadians();
    abstract public double getPiRadians();
    abstract public double getReal();
    abstract public double getImaginary();
    
    public GenComplex toGenComplex(){ return new GenComplex(getReal(),getImaginary()); }
    public ExpComplex toExpComplex(){ return new ExpComplex(getRadius(),getRadians()); }
    public PlrComplex toPlrComplex(){ return new PlrComplex(getRadius(),getRadians()); }
    
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
    GenComplex(ComplexNumber that){
        this(that.getReal(),that.getImaginary());
    }
    
    // methods
    public double getRadius(){ return Math.sqrt(Math.pow(real,2)+Math.pow(imaginary,2)); }
    public double getRadians(){ return Math.atan2(imaginary,real); }
    public double getPiRadians(){ return getRadians()/Math.PI; }
    public double getReal(){ return real; }
    public double getImaginary(){ return imaginary; }
    
    public String toString(){ return String.format("%f+%fi",real,imaginary); }
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
    PlrComplex(ComplexNumber that){
        this(that.getRadius(),that.getRadians());
    }
    // methods
    public double getRadius(){ return radius; }
    public double getRadians(){ return piRadians*Math.PI; }
    public double getPiRadians(){ return piRadians; }
    public double getReal(){ return radius*Math.cos(getRadians()); }
    public double getImaginary(){ return radius*Math.sin(getRadians()); }
    
    public String toString(){ return String.format("%f,%fpi",radius,piRadians); }
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
    ExpComplex(ComplexNumber that){
        super(that);
    }
    // methods
    @Override
    public String toString(){
        return String.format("%fexp(%f)",getRadius(),getPiRadians());
    }
}
