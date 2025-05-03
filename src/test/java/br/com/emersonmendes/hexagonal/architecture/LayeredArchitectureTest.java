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
        .layer("AdaptersIn").definedBy("..adapters.in..")
        .layer("AdaptersOut").definedBy("..adapters.out..")
        .layer("UseCases").definedBy("..application.core.usecase..")
        .layer("PortsIn").definedBy("..application.ports.in..")
        .layer("PortsOut").definedBy("..application.ports.out..")
        .layer("Domains").definedBy("..application.core.domain..")
        .whereLayer("AdaptersIn").mayNotBeAccessedByAnyLayer()
        .whereLayer("AdaptersOut").mayNotBeAccessedByAnyLayer()
        .whereLayer("UseCases").mayNotBeAccessedByAnyLayer()
        .whereLayer("PortsIn").mayOnlyBeAccessedByLayers("UseCases", "AdaptersIn")
        .whereLayer("PortsOut").mayOnlyBeAccessedByLayers("UseCases", "AdaptersOut")
        .whereLayer("Domains").mayOnlyBeAccessedByLayers(
            "PortsOut", "PortsIn", "UseCases", "AdaptersOut", "AdaptersIn"
        )

    ;

}
