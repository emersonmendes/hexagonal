package br.com.emersonmendes.hexagonal.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "br.com.emersonmendes.hexagonal")
public class LayeredArchTest {

    @ArchTest
    public static final ArchRule layeredArchTest = Architectures.layeredArchitecture()
        .consideringAllDependencies()
        .layer("AdaptersIn").definedBy("..adapters.in..")
        .layer("AdaptersOut").definedBy("..adapters.out..")
        .layer("UseCase").definedBy("..application.core.usecase..")
        .layer("PortsIn").definedBy("..application.ports.in..")
        .layer("PortsOut").definedBy("..application.ports.out..")
        .whereLayer("AdaptersIn").mayNotBeAccessedByAnyLayer()
        .whereLayer("AdaptersOut").mayNotBeAccessedByAnyLayer();

}
