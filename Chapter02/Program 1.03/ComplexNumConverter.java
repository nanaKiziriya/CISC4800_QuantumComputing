public class ComplexNumConverter{
    // convert between general (cartesian), polar, and exponential (Euler's formula) form for complex numbers
    public static void main(String[]args){
        //driver
    }
}

//
public class ComplexMath{

    public double real(double radius, double radians){ return radius*Math.cos(radians()); }
    public double imaginary(double radius, double radians){ return radius*Math.sin(radians()); }
    public double radius(double real, double imaginary){ return Math.sqrt(Math.pow(real,2)+Math.pow(imaginary,2)); }
    public double radians(double real, double imaginary){ return Math.atan2(imaginary,real); }
    
}


// ComplexNumber object classes
abstract class ComplexNumber implements Cloneable{
    static tolerance = 1; // for .equals()
    
    public ComplexNumber clone() throws CloneNotSupportedException{ return (ComplexNumber)super.clone(); }
    public boolean equals(ComplexNumber that){
        return ((this.getReal()-that.getReal())/Math.pow(10,tolerance) && (this.getImaginary()-this.getImaginary())/Math.pow(10,tolerance))
            || ((this.getPiRadians()-that.getPiRadians())/Math.pow(10,tolerance) && (this.getRadius()-that.getRadius())/Math.pow(10,tolerance));
    }

    abstract public void add(ComplexNumber that);
    abstract public void subtract(ComplexNumber that);
    abstract public void multiply(ComplexNumber that);
    abstract public void divide(ComplexNumber that);

    public void multiplyScalar(double d){ setRadius(d*getRadius()); }
    
    abstract public double getRadius();
    abstract public double getRadians();
    abstract public double getPiRadians();
    abstract public double getReal();
    abstract public double getImaginary()

    abstract public void setRadius(double d);
    abstract public void setRadians(double d);
    abstract public void setPiRadians(double d);
    abstract public void setReal(double d);
    abstract public void setImaginary(double d);

    abstract public ComplexNumber getConjugate();
    public double getConjugateProduct(){ return Math.pow(getRadius(),2); }
    
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
    public add(ComplexNumber that){
        real = real + that.getReal();
        imaginary = imaginary + that.getImaginary();
    }
    public subtract(ComplexNumber that){
        real = real - that.getReal();
        imaginary = imaginary - that.getImaginary();
    }
    public multiply(ComplexNumber that){
        real = real*that.getReal() - imaginary*that.getImaginary();
        imaginary = real*that.getImaginary() + imaginary*that.getReal();
    }
    public divide(ComplexNumber that){ // multiply conjugate to numerator and denominator
        multiply(that.getConjugate()); // multiplies numerator
        divide(that.getConjugateProduct()); // divide by (double)denominator, NOT recursive
    }
    
    public double getRadius(){ return radius(real,imaginary); }
    public double getRadians(){ return radians(real,imaginary); }
    public double getReal(){ return real; }
    public double getImaginary(){ return imaginary; }

    public void setRadius(double d){
        double scalingFactor = d/getRadius();
        real *= scalingFactor;
        imaginary *= scalingFactor;
    }
    public void setRadians(double d){
        real = ComplexMath.real(getRadius(),d);
        imaginary = ComplexMath.imaginary(getRadius(),d);
    }
    public void setReal(double d){ real = d; }
    public void setImaginary(double d){ imaginary = d; }

    public ComplexNumber getConjugate(){ return new GenComplex(real,-1*imaginary); }
    
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
    public double getReal(){ return real(radius,getRadians()); }
    public double getImaginary(){ return imaginary(radius,getRadians()); }
    
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
