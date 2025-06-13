package br.com.resenhasociocultural.apiresenha.testdata;

import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.repository.YouthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class YouthTestData {

    private YouthRepository youthRepository;

    YouthTestData(YouthRepository youthRepository){
        this.youthRepository = youthRepository;
    }

    @Transactional
    public void createYouthTestData(){
    ArrayList<Youth> youths = new ArrayList<>();
        youths.add(new Youth(
                null,
                "Ana", "Silva",LocalDate.of(1995, 5, 15), "11111111111",
                "Maria Silva", "João Silva", "Carlos Silva", "Tio", "11987654321",
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Bruno", "Souza", LocalDate.of(1998, 8, 22), "22222222222",
                "Carla Souza", null, "Laura Mendes", "Amiga", "21912345678",
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Carlos", "Oliveira", LocalDate.of(2000, 1, 10), "33333333333",
                "Fernanda Oliveira", "Ricardo Oliveira", "Fernanda Oliveira", "Mãe", "31988887777",
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Daniela", "Pereira", LocalDate.of(1997, 11, 30), "44444444444",
                "Juliana Pereira", null, "Roberto Alves", "Avô", "41977776666",
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Eduardo", "Costa", LocalDate.of(2001, 3, 3), "55555555555",
                "Patricia Costa", "Marcos Costa", "Marcos Costa", "Pai", "51966665555",
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Fernanda", "Lima", null, "66666666666", // birthDate nulo
                "Adriana Lima", "Paulo Lima", "Sofia Lima", "Irmã", "61955554444",
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Gustavo", "Almeida", LocalDate.of(1999, 7, 7), "77777777777",
                "Sandra Almeida", null, "Sandra Almeida", "Mãe", "71944443333",
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Helena", "Rocha", LocalDate.of(2002, 2, 20), "88888888888",
                "Beatriz Rocha", "Felipe Rocha", null, null, null, // Sem contato de emergência
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Igor", "Nunes", LocalDate.of(1996, 12, 12), "99999999999",
                "Vanessa Nunes", "Leonardo Nunes", "Leonardo Nunes", "Pai", "81933332222",
                null // creationDate
        ));

        youths.add(new Youth(
                null, // id
                "Julia", "Martins", LocalDate.of(2003, 10, 25), "10101010101",
                "Debora Martins", null, "Teresa Santos", "Avó", "91922221111",
                null // creationDate
        ));

        List<Youth> savedYouths = youthRepository.saveAll(youths);
        if (savedYouths.size() == youths.size()){
            log.info("Os dados de teste de Youth foram adicionados com sucesso");
        } else {
            log.warn("Não foi possível adicionar os dados de teste de Youth");
        }
    }
}
