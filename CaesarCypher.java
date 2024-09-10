import java.util.Scanner;
class CaesarCypher{

    static String Encrypt(int key, String kalimat){
        String result = "";
        for (int i = 0; i < kalimat.length() ; i++){
            if (kalimat.charAt(i) >= 65 && kalimat.charAt(i) <= 90){
                result += Character.toString((kalimat.charAt(i)+ key - 65) % 26 + 65);
            }
            else if (kalimat.charAt(i) >= 97 && kalimat.charAt(i) <= 123){
                result += Character.toString((kalimat.charAt(i)+ key - 97) % 26 + 97);
            }
            else{
                result += kalimat.charAt(i);
            }
        }
        return result;
    }

    static String Decrypt(int key, String kalimat){
        String result = "";
        for (int i = 0; i < kalimat.length() ; i++){
            if (kalimat.charAt(i) >= 65 && kalimat.charAt(i) <= 90){
                result += Character.toString((kalimat.charAt(i)- key - 90) % 26 + 90);
            }
            else if (kalimat.charAt(i) >= 97 && kalimat.charAt(i) <= 122){
                result += Character.toString((kalimat.charAt(i)- key - 122) % 26 + 122);
            }
            else{
                result += kalimat.charAt(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int key= cin.nextInt();
        cin.nextLine();
        String kata = cin.nextLine();
        
        System.out.println(Decrypt(key, kata));

        cin.close();
    }
}