package fruitnew.architecturetest;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ArchitectureTest{

    @Test
    void testLayeredArchitecture() {
        JavaClasses importedClasses = new ClassFileImporter().withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS).importPackages("fruitnew");

        ArchRule rule = layeredArchitecture().consideringAllDependencies()
                .layer("Controller").definedBy("fruitnew.controller..")
                .layer("Service").definedBy("fruitnew.service..")
                .layer("Repository").definedBy("fruitnew.repository..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

        rule.check(importedClasses);
    }
}