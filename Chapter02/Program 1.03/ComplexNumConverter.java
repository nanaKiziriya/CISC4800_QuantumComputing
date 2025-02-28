public class ComplexNumConverter{
    // convert between general (cartesian), polar, and exponential (Euler's formula) form for complex numbers

    public static void main(String[]args){
        //driver
    }
}

public class ComplexNumber{
    int radius;
    int theta
    
}

absract class ComplexNumber{
    public String toString();
}

class GenComplex extends ComplexNumber{
    double real, imaginary;
    public String toString(){

    }
}
    

class PlrComplex extends ComplexNumber{
    double radius, piRadians;
    public String toString(){
        return String.format("%f,%fpi",radius,piRadians);
    }
}

class ExpComplex extends PlrComplex{
    // r*exp(theta*i)
    public String toString(){
        return 
    }
}
