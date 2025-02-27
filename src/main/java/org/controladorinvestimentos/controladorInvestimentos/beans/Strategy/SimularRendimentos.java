package org.controladorinvestimentos.controladorInvestimentos.beans.Strategy;

public class SimularRendimentos {

        //passar como valor da inicial, o valor da carteira a ser analizada
        public static double calcularValorFuturo(double valorInicial, double taxaAnual, int anos) {
            return valorInicial * Math.pow(1 + (taxaAnual / 100), anos);
        }

        public static double calcularValorFuturoComAportes(double valorInicial, double taxaAnual, double aporteMensal, int anos) {
            double montante = valorInicial;
            double taxaMensal = taxaAnual / 12 / 100;
            int meses = anos * 12;

            for (int i = 0; i < meses; i++) {
                montante += aporteMensal;
                montante *= (1 + taxaMensal);
            }
            return montante;
        }
}
