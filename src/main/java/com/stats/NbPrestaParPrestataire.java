package com.stats;

public class NbPrestaParPrestataire {

        private String nomPrestataire;
        private int nombreEngagements;
        private int totalPrixPaye;

        public NbPrestaParPrestataire(String nomPrestataire, int nombreEngagements, int totalPrixPaye) {
            this.nomPrestataire = nomPrestataire;
            this.nombreEngagements = nombreEngagements;
            this.totalPrixPaye = totalPrixPaye;
        }

        public String getNomPrestataire() {
            return nomPrestataire;
        }

        public String getNombreEngagements() {
            return String.valueOf(nombreEngagements);
        }

        public String getTotalPrixPaye() {
            return String.valueOf(totalPrixPaye);
        }
}
