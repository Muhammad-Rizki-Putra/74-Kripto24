import java.util.Scanner;

public final class HillCypher {
    private HillCypher() {
    }

    static Scanner userInput = new Scanner(System.in);
    
    static void encrypt(String message) {
        message = message.toUpperCase();

        System.out.print("Masukkan ukuran matriks : ");
        int matrixSize = userInput.nextInt();
        System.out.print("Masukkan Kunci : ");
        int[][] keyMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                keyMatrix[i][j] = userInput.nextInt();
            }
        }
  
        validateDeterminant(keyMatrix, matrixSize);

        int[][] messageVector = new int[matrixSize][1];
        String cipherText = "";
        int[][] cipherMatrix = new int[matrixSize][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < matrixSize; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                j++;
            }
            int x;
            int i;
            for (i = 0; i < matrixSize; i++) {
                cipherMatrix[i][0] = 0;

                for (x = 0; x < matrixSize; x++) {
                    cipherMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
                }
                cipherMatrix[i][0] = cipherMatrix[i][0] % 26;
            }
            for (i = 0; i < matrixSize; i++) {
                cipherText += (char) (cipherMatrix[i][0] + 65);
            }
        }
        System.out.println("Ciphertext: " + cipherText);
    }

    static void FindKey() {

        System.out.print("Masukkan ukuran matriks : ");
        int matrixSize = userInput.nextInt();

        System.out.print("Masukkan plain text invers dalam matrix : ");
        int[][] PlainTextInvers = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                PlainTextInvers[i][j] = userInput.nextInt();
            }
        }

        System.out.print("Masukkan Cipher text dalam matriks : ");
        int[][] CipherText = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                CipherText[i][j] = userInput.nextInt();
            }
        }
  
        int Key[][] = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++)
                    Key[i][j] += PlainTextInvers[i][k] * CipherText[k][j];
            }
        }

        for (int i = 0 ; i < matrixSize; i++){
            for (int j = 0; j < matrixSize; j++){
                Key[i][j] %= 26;
            }
        }

        System.out.print("Key = ");
        for (int i = 0; i < matrixSize; i++){
            for (int j = 0; j < matrixSize; j++){
                System.out.print(Key[i][j]+" ");
            }
        }
    }

    static void decrypt(String message) {
        message = message.toUpperCase();
     
        System.out.print("Masukkan ukuran matriks : ");
        int n = userInput.nextInt();
        System.out.print("Masukkan Kunci (Matrix inversed) : ");
        int[][] keyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMatrix[i][j] = userInput.nextInt();
            }
        }
    
        validateDeterminant(keyMatrix, n);

        int[][] messageVector = new int[n][1];
        String plainText = "";
        int[][] plainMatrix = new int[n][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < n; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                j++;
            }
            int x;
            int i;
            for (i = 0; i < n; i++) {
                plainMatrix[i][0] = 0;

                for (x = 0; x < n; x++) {
                    plainMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
                }

                plainMatrix[i][0] = plainMatrix[i][0] % 26;
            }
            for (i = 0; i < n; i++) {
                plainText += (char) (plainMatrix[i][0] + 65);
            }
        }
        System.out.println("Plaintext: " + plainText);
    }

    public static int determinant(int[][] a, int n) {
        int det = 0;
        int sign = 1;
        int p = 0;
        int q = 0;

        if (n == 1) {
            det = a[0][0];
        } else {
            int[][] b = new int[n - 1][n - 1];
            for (int x = 0; x < n; x++) {
                p = 0;
                q = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j != x) {
                            b[p][q++] = a[i][j];
                            if (q % (n - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                det = det + a[0][x] * determinant(b, n - 1) * sign;
                sign = -sign;
            }
        }
        return det;
    }

    static void hillCipher() {
        String message;
        System.out.println("Ingin diapakan pesannya?");
        System.out.println("1 : Encrypt");
        System.out.println("2 : Decrypt");
        System.out.println("3 : Find key");
        System.out.print("Opsi : ");
        short sc = userInput.nextShort();
        if (sc == 1) {
            System.out.print("Masukkan Pesan : ");
            message = userInput.nextLine();
            encrypt(message);
        } else if (sc == 2) {
            System.out.print("Masukkan Pesan : ");
            message = userInput.nextLine();
            decrypt(message);
        } else if (sc == 3) {
            FindKey();
        } else {
            System.out.println("Invalid input");
        }
    }

    static void validateDeterminant(int[][] keyMatrix, int n) {
        if (determinant(keyMatrix, n) % 26 == 0) {
            System.out.println("Invalid key, determinan = 0.");
        }
    }

    public static void main(String[] args) {
        hillCipher();
    }
}