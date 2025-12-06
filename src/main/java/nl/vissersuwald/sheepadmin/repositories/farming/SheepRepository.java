package nl.vissersuwald.sheepadmin.repositories.farming;

import nl.vissersuwald.sheepadmin.dto.SheepListDto;
import nl.vissersuwald.sheepadmin.models.farming.Sheep;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SheepRepository extends JpaRepository<Sheep, Long> {

    @Query(value = """
            SELECT new nl.vissersuwald.sheepadmin.dto.SheepListDto(
                 s.id,
                 s.name,
                 s.dateOfBirth,
                 s.fatherId,
                 s.motherId,
                 s.fosterMotherId,
                 s.yearOfBirth,
                 s.dateOfDeath,
                 s.reasonOfDeath,
                 s.colour,
                 s.oldName,
                 s.boughtFrom,
                 s.gender,
                 sum(
                     COALESCE(bty.ewes, 0) +
                     COALESCE(bty.rams, 0) +
                     COALESCE(bty.fosterEwes, 0) +
                     COALESCE(bty.fosterRams, 0) -
                     COALESCE(bty.fosteredEwes, 0) -
                     COALESCE(bty.fosteredRams, 0) -
                     COALESCE(bty.eweInfantMortality, 0) -
                     COALESCE(bty.ramInfantMortality, 0) -
                     COALESCE(bty.infantMortality, 0)
                 ),
                 sum(
                     COALESCE(bpy.ewes, 0) +
                     COALESCE(bpy.rams, 0) +
                     COALESCE(bpy.fosterEwes, 0) +
                     COALESCE(bpy.fosterRams, 0) -
                     COALESCE(bpy.fosteredEwes, 0) -
                     COALESCE(bpy.fosteredRams, 0) -
                     COALESCE(bpy.eweInfantMortality, 0) -
                     COALESCE(bpy.ramInfantMortality, 0) -
                     COALESCE(bpy.infantMortality, 0)
                 )
            )
            FROM Sheep s
            LEFT JOIN s.births bty ON bty.id.yearOfBirth = :currentYear
            LEFT JOIN s.births bpy ON bpy.id.yearOfBirth = :previousYear
            WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
            GROUP BY
               s.id,
               s.name,
               s.dateOfBirth,
               s.fatherId,
               s.motherId,
               s.fosterMotherId,
               s.yearOfBirth,
               s.dateOfDeath,
               s.reasonOfDeath,
               s.colour,
               s.oldName,
               s.boughtFrom,
               s.gender
            """,
            countQuery = """
            SELECT count(s.id)
            FROM Sheep s
            WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
            """)
    Page<SheepListDto> findSheepList(@Param("currentYear") Long currentYear,
                                     @Param("previousYear") Long previousYear,
                                     @Param("name") String name,
                                     Pageable pageable);
}
