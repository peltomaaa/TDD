package com.company;


public final class rovar
{

    private static String lower_consonants = "bcdgfhjklmnpqrstvwxz";
    private static String upper_consonants = "BCDFGHJKLMNPQRSTVWXZ";

    /// <summary>
    /// Encode the string in rovarspraket.
    /// </summary>
    /// <param name="normal">Normal string.</param>
    /// <returns>Encoded string.</returns>
    public static String enrov(String normal)
    {
        if (normal == null)
            return null;
        StringBuilder builder = new StringBuilder();

        for(char c : normal.toCharArray())
        if (lower_consonants.contains(String.valueOf(c)))
            builder.append(c + "o" + c);
        else if (upper_consonants.contains(String.valueOf(c)))
            builder.append(c + "O" + c) ;
        else
            builder.append(c);

        return builder.toString();
    }


    /// <summary>
    /// Decode a string from rovarspraket.
    /// </summary>
    /// <param name="rov">Encoded string.</param>
    /// <returns>Normal string.</returns>
    public static String derov(String rov)
    {
        if (rov == null)
            return null;

        for(char c : lower_consonants.toCharArray()) {
            String find = (String.valueOf(c) + "o" + String.valueOf(c));
            rov = rov.replace(find, String.valueOf(c));
        }

        for(char c : upper_consonants.toCharArray()){
            String find = (String.valueOf(c) + "O" + String.valueOf(c));
            rov = rov.replace(find, String.valueOf(c));
        }
        return rov;
    }

    public static void main(String[] args) {
        //rovar testRover = new rovar();
        String string = "Gris";

        System.out.println(string);
        string = rovar.enrov(string);
        System.out.println(string);
        string = rovar.derov(string);
        System.out.println(string);
    }
}

