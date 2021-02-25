package ar.com.ada.second.online.endpointsdefinition.controller;

import ar.com.ada.second.online.endpointsdefinition.model.dto.ItemDTO;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.URISyntax;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping(value = "/items")
public class ItemController {
    private List<ItemDTO> items = new ArrayList<>(Arrays.asList(
            new ItemDTO(1L, "CocaCola", "Bebida gaseosa", 123456),
            new ItemDTO(2L, "Palitos Salados", "Snack", 123457),
            new ItemDTO(3L, "Cerveza", "Heineken", 123458)
    ));

    @GetMapping({"/", ""})
    public ResponseEntity getItemsMethod() {

        return ResponseEntity.ok().body(items);


    }

    @PostMapping({"/", ""})
    public ResponseEntity postItemMethod(@Valid @RequestBody ItemDTO item) throws URISyntaxException {
        ItemDTO lastItem = items.stream()
                .reduce((first, second) -> second)//reduce, skip, retorna un objeto y  retornan el ultimo
                .orElse(null);

        Long id = (lastItem == null)
                ? 1
                : lastItem.getId() + 1;

        item.setId(id);

        items.add(item);

        URI uri = new URI("/items/" + id);
        return ResponseEntity.created(uri).body(item);

    }


    @GetMapping({"/{itemId}", "/{itemId}/"})
    public ResponseEntity getItemByIdMethod(@PathVariable long itemId) {
        ItemDTO itemById = items.stream()
                .filter((item) -> {
                    return item.getId().equals(itemId); //item.getId retorna un boolean
                })
                .findFirst()
                .orElse(null);
        HttpStatus httpStatus = (itemById == null)
                ? HttpStatus.NOT_FOUND
                : HttpStatus.OK;
        return ResponseEntity.status(httpStatus).body(itemById);
    }

    @DeleteMapping({"/{itemId}", "/itemId}/"})
    public ResponseEntity deleteItemByIdMethod(@PathVariable Long itemId) {
        Boolean hasDelete = items.removeIf(item -> item.getId().equals(itemId));//removeIf retorna un boolean
        Map<String, String> itemNotFound = new HashMap<>();
        itemNotFound.put("error", HttpStatus.BAD_REQUEST.toString());
        itemNotFound.put("message", "item id not exist");
        return (hasDelete)
                ? ResponseEntity.noContent().build()//204
                : ResponseEntity.badRequest().body(itemNotFound);//400
    }

    @PutMapping({"/{itemId}", "/{itemId}/"})
    public ResponseEntity putItemByIdMethod(@Valid @RequestBody ItemDTO itemData, @PathVariable Long itemId) {

        Map<String, String> itemNotFound = new HashMap<>();
        itemNotFound.put("error", HttpStatus.BAD_REQUEST.toString());
        itemNotFound.put("message", "item id not exist");


        // con el metodo stream se crea una lista filtrada con filter
        ItemDTO itemById = items.stream()
                .filter(item -> item.getId().equals(itemId))//filter retorna un boolean
                // peek metodo que es muy parecido a for each accede a los elementos de la lista, se usa desp de filter y no retorna nada
                .peek(item -> {
                    //setea los campos
                    item.setName(itemData.getName());
                    item.setDescription(itemData.getDescription());
                    item.setNumberBarcode(itemData.getNumberBarcode());
                })
                .findFirst()//extraigo el id para acceder al elemento
                .orElse(null);

        return (itemById != null)
                //retorna si es distinto a null y muestro con el item que se actualizo con los datos
                ? ResponseEntity.ok().body(itemById)
                //si es igual mando un badRequest ya que no cumple con la condicion  itemNotFound.put("message", "item id not exist");
                : ResponseEntity.badRequest().body(itemNotFound);
    }

    @PatchMapping({"/{itemId}", "/{itemId}/"})
    public ResponseEntity patchItemByIdMethod(@RequestBody ItemDTO itemData, @PathVariable Long itemId) {

        Map<String, String> itemNotFound = new HashMap<>();
        itemNotFound.put("error", HttpStatus.BAD_REQUEST.toString());
        itemNotFound.put("message", "item id not exist");

        ItemDTO itemById = items.stream()
                .filter(item -> item.getId().equals(itemId))
                .peek(item -> {
                    // "   hola mundo   ".trim() => "hola mundo"
                    if (itemData.getName() != null && !itemData.getName().trim().equals(""))
                        //si se cumple la condicion le seteo el nuevo nombre
                        item.setName(itemData.getName());

                    if (itemData.getDescription() != null && !itemData.getDescription().trim().equals(""))
                        item.setDescription(itemData.getDescription());

                    if (itemData.getNumberBarcode() != null)
                        item.setNumberBarcode(itemData.getNumberBarcode());
                })
                .findFirst()
                .orElse(null);

        return (itemById != null)
                ? ResponseEntity.ok().body(itemById)
                : ResponseEntity.badRequest().body(itemNotFound);
    }
}



