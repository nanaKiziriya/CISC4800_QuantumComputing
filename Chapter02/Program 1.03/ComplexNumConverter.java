public class ComplexNumConverter{
    // convert between general (cartesian), polar, and exponential (Euler's formula) form for complex numbers
    public static void main(String[]args){
        //driver
    }
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

    abstract public void add(double d);
    abstract public void subtract(double d);
    abstract public void multiply(double d);
    abstract public void divide(double d);
    
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
    abstract public double getConjugateProduct(){ return Math.pow(radius,2); }
    
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
        multiply(that.conjugate); // multiplies numerator
        divide(that.multiplyConjugate()); // divide by denominator
    }
    
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
