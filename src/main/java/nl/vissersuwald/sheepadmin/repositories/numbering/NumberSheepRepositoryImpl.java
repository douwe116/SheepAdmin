package nl.vissersuwald.sheepadmin.repositories.numbering;

import nl.vissersuwald.sheepadmin.models.ActiveParameters;
import nl.vissersuwald.sheepadmin.models.Birth;
import nl.vissersuwald.sheepadmin.models.Sheep;
import nl.vissersuwald.sheepadmin.models.numbering.NewNumber;
import nl.vissersuwald.sheepadmin.models.numbering.NumberSheep;
import nl.vissersuwald.sheepadmin.repositories.SheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class NumberSheepRepositoryImpl implements NumberSheepRepository{
    private final EntityManager entityManager;
    @Autowired
    private SheepRepository sheepRepository;

    NumberSheepRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Sheep> addNumberingToBirth(NumberSheep numberSheep) {
        // Check if the amount males/females matches the births
        Birth birth = entityManager.getReference(Birth.class,numberSheep.getBirthId());
        Long rams = 0L;
        Long ewes = 0L;

        for(NewNumber newNumber:numberSheep.getNewNumbers()){
            if(newNumber.getGender() == "M") { rams += 1L;};
            if(newNumber.getGender() == "F") { ewes += 1L;};
        }

        assert(rams.equals(birth.getRams()));
        assert(ewes.equals(birth.getEwes()));

        // Create the new sheep and add them to a list
        List<Sheep> newSheep = new ArrayList<Sheep>();

        for(NewNumber newNumber:numberSheep.getNewNumbers()){
            Sheep sheep = new Sheep();
            sheep.setGender(newNumber.getGender());
            sheep.setName(newNumber.getNumber());
            sheep.setDateOfBirth(birth.getDateOfBirth());
            sheep.setMotherId(birth.getMotherId());
            sheep.setYearOfBirth(birth.getYearOfBirth());

            // Save to the database
            sheep = sheepRepository.saveAndFlush(sheep);
            newSheep.add(sheep);
        }

        // Update the birth status
        birth.setStatus("Numbered");

        return newSheep;
    }

    @Override
    public List<ActiveParameters> findAll() {
        return null;
    }

    @Override
    public List<ActiveParameters> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ActiveParameters> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ActiveParameters> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ActiveParameters entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ActiveParameters> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ActiveParameters> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ActiveParameters> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ActiveParameters> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ActiveParameters> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ActiveParameters> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ActiveParameters> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ActiveParameters getOne(Long aLong) {
        return null;
    }

    @Override
    public ActiveParameters getById(Long aLong) {
        return null;
    }

    @Override
    public ActiveParameters getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ActiveParameters> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ActiveParameters> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ActiveParameters> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ActiveParameters> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ActiveParameters> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ActiveParameters> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ActiveParameters, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
