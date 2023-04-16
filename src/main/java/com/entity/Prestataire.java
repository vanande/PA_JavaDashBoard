package com.entity;

public class Prestataire {

        private String nomPrestataire;
        private String metierPrestataire;
        private int nombreEngagements;
        private int totalPrixPaye;

        public Prestataire(String nomPrestataire, int nombreEngagements, int totalPrixPaye, String metierPrestataire) {
            this.nomPrestataire = nomPrestataire;
            this.nombreEngagements = nombreEngagements;
            this.totalPrixPaye = totalPrixPaye;
            this.metierPrestataire = metierPrestataire;
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

        public String getMetierPrestataire() {
            return metierPrestataire;
        }
}
