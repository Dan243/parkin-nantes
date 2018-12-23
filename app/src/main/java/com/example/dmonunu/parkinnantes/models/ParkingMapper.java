package com.example.dmonunu.parkinnantes.models;

import com.example.dmonunu.parkinnantes.utilities.ParkingParser;

import java.util.ArrayList;
import java.util.List;

public class ParkingMapper {

    public static List<LightParking> createLightParkings(List<DispoModel> dispoModels, List<HoraireModel> horaireModels, List<ParkingModel> parkingModels) {
        List<LightParking> lightParkings = new ArrayList<>();
        for (ParkingModel parkingModel : parkingModels) {
            DispoModel dispoModel = getDispoModelById(dispoModels, parkingModel.getIdobj());
            HoraireModel horaireModel = getHoraireModelByid(horaireModels, parkingModel.getIdobj());
            lightParkings.add(createLightParking(dispoModel, horaireModel, parkingModel));
        }

        return lightParkings;
    }

    private static DispoModel getDispoModelById(List<DispoModel> dispoModels, String id) {
        for (DispoModel dispoModel : dispoModels) {
            if (id.equals(dispoModel.getIdobj())) {
                return dispoModel;
            }
        }

        return null;
    }

    private static HoraireModel getHoraireModelByid(List<HoraireModel> horaireModels, String id) {
        for (HoraireModel horaireModel : horaireModels) {
            if (id.equals(horaireModel.getIdobj())) {
                return horaireModel;
            }
        }

        return null;
    }

    public static LightParking createLightParking(DispoModel dispoModel, HoraireModel horaireModel, ParkingModel parkingModel){

        if (parkingModel != null) {
            String idobj = parkingModel.getIdobj();
            int capaciteTotale = parkingModel.getCarCapacity();
            String telephone = parkingModel.getTelephone();
            String moyenPaiement = parkingModel.getPaymentWays();
            double latitude = parkingModel.getLatitude();
            double longitude = parkingModel.getLongitude();
            String adresse = parkingModel.getAddress();
            String nomParking = parkingModel.getFullname();
            String presentation = parkingModel.getPresentation();
            String acces_transports_communs = parkingModel.getPublicTransportAccess();
            int stationnement_velo = parkingModel.getBikeCapacity();
            int capacite_moto = parkingModel.getMotoCapacity();
            int capacite_pmr = parkingModel.getPmrCapacity();
            boolean isCreditCardAvailable = false;
            boolean isCashAvailable = false;
            boolean isChequeAvailable = false;
            boolean isTotalGRCardAvailable = false;
            if (moyenPaiement != null ){
                 isCreditCardAvailable = ParkingParser.isCreditCardAvailable(moyenPaiement);
                 isCashAvailable = ParkingParser.isCashAvailable(moyenPaiement);
                 isChequeAvailable = ParkingParser.isChequeAvailable(moyenPaiement);
                 isTotalGRCardAvailable = ParkingParser.isTotalGRAvailable(moyenPaiement);
            }

            boolean isLigneOneNear = false;
            boolean isLigneTwoNear = false;
            boolean isLigneThreeNear = false;
            boolean isLigneFourNear = false;
            if (acces_transports_communs != null ) {
                 isLigneOneNear = ParkingParser.isLigneOneNear(acces_transports_communs);
                 isLigneTwoNear = ParkingParser.isLigneTwoNear(acces_transports_communs);
                 isLigneThreeNear = ParkingParser.isLigneThreeNear(acces_transports_communs);
                 isLigneFourNear = ParkingParser.isLigneFourNear(acces_transports_communs);
            }



            if (horaireModel != null) {

                String heureDebut = horaireModel.getHeure_debut();

                String heureFin = horaireModel.getHeure_fin();
                String dateDebut = horaireModel.getDate_debut();
                String dateFin = horaireModel.getDate_fin();

                if (dispoModel != null) {
                    int nbPlaceDispo = dispoModel.getGrp_disponible();

                    return new LightParking.LightParkingBuilder()
                            .idObj(idobj)
                            .nomParking(nomParking)
                            .nbPlaceDispo(nbPlaceDispo)
                            .capaciteTotale(capaciteTotale)
                            .telephone(telephone)
                            .moyenPaiement(moyenPaiement)
                            .heureDebut(heureDebut)
                            .heureFin(heureFin)
                            .latitude(latitude)
                            .longitude(longitude)
                            .dateDebut(dateDebut)
                            .dateFin(dateFin)
                            .adresse(adresse)
                            .acces_transports_communs(acces_transports_communs)
                            .capacite_moto(capacite_moto)
                            .capacite_pmr(capacite_pmr)
                            .stationnement_velo(stationnement_velo)
                            .presentation(presentation)
                            .isCashAvailable(isCashAvailable)
                            .isChequeAvailable(isChequeAvailable)
                            .isCreditCardAvailable(isCreditCardAvailable)
                            .isTotalGRCardAvailable(isTotalGRCardAvailable)
                            .isLigneOneNear(isLigneOneNear)
                            .isLigneTwoNear(isLigneTwoNear)
                            .isLigneThreeNear(isLigneThreeNear)
                            .isLigneFourNear(isLigneFourNear)
                            .build();
                }
            }
        }
        return null;
    }
}
