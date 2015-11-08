package com.company;

import java.util.Scanner;

/**
 * Created by hbrtxito on 10/30/2015.
 */
public class changeofSuits {


    /**
     * Este method retorna um unico caracter
     * Valida se o usuario enviou apena um caracter caso contrario envia uma mensagem uma prompt message.
     */

    protected static char promptForChar(String promt) {
        Scanner scanner = new Scanner(System.in);
        char c = ' ';
        boolean valid = false;
        while (!valid) {
            System.out.println(promt);
            String s = scanner.nextLine();
            if (s.length() == 1) {
                c = s.charAt(0);
                valid = true;
            }
            else {
                c = promptForChar(promt);
            }
        }
        return c;
    }
}