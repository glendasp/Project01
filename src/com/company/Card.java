package com.company;

/**
 * Created by hbrtxito on 11/1/2015.
 */
public class Card {


    private char rank = ' ';
    private char suit = ' ';

    /**
     * Este method retorna uma string junçäo de rand e suit, adicionando uma string vazia
     * para converter o caracter em uma string
     */
    // This method is to return a String  (join  rank and suit)
    // Adding a empty String  to convert the characters to a string
    public String toString() {
        String rs = "" + rank + suit;
        return rs;
    }

    private static final String RANKS = "A23456789TJQK";
    private static final String SUITS = "HDCS";

    /**
     * O contructor é o respossavel pela construção do card, por isso a class Chars esta vazia
     *
     */
    public Card(String rs) {
        if (rs.length() == 2) { //Força 2 caracters por vez
            // I get rs.chartAT(0)(first position) and  rs.chartAT(1)(second position)
            char r = rs.charAt(0);
            char s = rs.charAt(1);
            /**
             * IndexOf para achar o caracter sendo usado
             */
            int ri = RANKS.indexOf(r);
            int si = SUITS.indexOf(s);

            //valida
            if (ri > -1 && si > -1) {
                rank = r;
                suit = s;
            }
        }

    }

    /**
     * Este construtor é para criar um cartão com numero integer
     * total do baralho 52
     */

    public Card(int id) {
        id = id % 52;
     if (id < 0) { // caso o numero seja negativo, faz virar positivo
            id = id * -1;
        }
        rank = RANKS.charAt(id % 13);//% pega a primeira posição dos baralho (0-12)
        suit = SUITS.charAt(id / 13);// / pega o suit do baralho (0-3)

    }

    // pega o valor primitivel da variavel
    public char getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    //Retorna caso seja valido
    public boolean isValid() {
        boolean valid = false;
        // Suit nao pode ser vazio
        if (suit != ' ') {
            valid = true;
        }
        return valid;
    }

    // Method para comprar se o baralho é igual ao outro
    public boolean equals (Card card){
        boolean equals = false ;

        if ((card.getSuit()==suit) && (card.getRank() == rank)){
            equals =true ;
        }
        return equals ;
    }
   //Verifica se o valor de uma carta é maior que a outra
    public boolean isGreaterthan (Card card){
        boolean greaterthan = false ;
        //Pega o suit e o rank do baralho
        char cardSuit = card.getSuit();
        char cardRank =  card.getRank() ;
        //Verifica se o index do suit in suits é maior que o indexOF do baralho, baseado na ordem do suit  and Ranks= "A23456789TJQK".
        if (SUITS.indexOf(suit)> SUITS.indexOf(cardSuit)){
            greaterthan = true ;
        }

        //Se a carta do jogador for o mesmo da carta na mesa.
        else if (suit == cardSuit) {
            if (RANKS.indexOf(rank)> RANKS.indexOf(cardRank)){
                greaterthan = true ;

            }

        }
        return greaterthan ;
    }

    //Verifica se o caracter é valido
    public static boolean isValidSuit( char c){
        boolean valid = false ;
        if (SUITS.indexOf(c)>-1){
            valid =true ;
        }
        return valid;
    }
}
