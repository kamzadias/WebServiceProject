package services;

import entities.Establishment;
import repositories.interfaces.IEstablishmentRepository;
import services.interfaces.IEstablishmentService;

import javax.inject.Inject;

public class EstablishmentService implements IEstablishmentService {
    @Inject
    private IEstablishmentRepository establishmentRepository;

    @Override
    public Establishment getEstablishmentById(int id) {
        return establishmentRepository.get(id);
    }
}
