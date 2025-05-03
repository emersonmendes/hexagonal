package br.com.emersonmendes.hexagonal.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "br.com.emersonmendes.hexagonal")
public class LayeredArchitectureTest {

    @ArchTest
    public static final ArchRule layeredArchTest = Architectures.layeredArchitecture()
        .consideringAllDependencies()
        .layer("AdaptersIn").definedBy("..hexagonal.adapters.in..")
        .layer("AdaptersOut").definedBy("..hexagonal.adapters.out..")
        .layer("UseCases").definedBy("..hexagonal.application.core.usecase..")
        .layer("PortsIn").definedBy("..hexagonal.application.ports.in..")
        .layer("PortsOut").definedBy("..hexagonal.application.ports.out..")
        .layer("Domains").definedBy("..hexagonal.application.core.domain..")
        .layer("Repositories").definedBy("..hexagonal.adapters.out.repository..")
        .whereLayer("AdaptersIn").mayNotBeAccessedByAnyLayer()
        .whereLayer("AdaptersOut").mayNotBeAccessedByAnyLayer()
        .whereLayer("UseCases").mayNotBeAccessedByAnyLayer()
        .whereLayer("PortsIn").mayOnlyBeAccessedByLayers("UseCases", "AdaptersIn")
        .whereLayer("PortsOut").mayOnlyBeAccessedByLayers("UseCases", "AdaptersOut")
        .whereLayer("Repositories").mayOnlyBeAccessedByLayers("AdaptersOut")
        .whereLayer("Domains").mayOnlyBeAccessedByLayers(
            "PortsOut", "PortsIn", "UseCases", "AdaptersOut", "AdaptersIn"
        )

    ;

}
