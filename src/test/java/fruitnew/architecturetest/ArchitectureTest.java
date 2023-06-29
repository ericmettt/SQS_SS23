package fruitnew.architecturetest;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

// This class defines an architecture test using ArchUnit to validate the layered architecture of the "fruitnew" package.
// It ensures that the classes in the Controller, Service, and Repository layers are properly structured and do not violate
// the defined dependencies between layers.
class ArchitectureTest{

    @Test
    void testLayeredArchitecture() {
        JavaClasses importedClasses = new ClassFileImporter().withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS).importPackages("fruitnew");
        // Define the layered architecture rules
        ArchRule rule = layeredArchitecture().consideringAllDependencies()
                .layer("Controller").definedBy("fruitnew.controller..")
                .layer("Service").definedBy("fruitnew.service..")
                .layer("Repository").definedBy("fruitnew.repository..")
                // Define the constraints between layers
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");
        // Check if the imported classes adhere to the layered architecture rules
        rule.check(importedClasses);
    }
}