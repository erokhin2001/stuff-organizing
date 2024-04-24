package com.art.stuff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.art.stuff.domain.Item;
import com.art.stuff.domain.ItemRepository;
import com.art.stuff.domain.Location;
import com.art.stuff.domain.Type;

@DataJpaTest
public class ItemRepsitoryTest {
    
    @Autowired
    private ItemRepository repository; 

    @Test
    public void createNewItem() {
        Type type = new Type("Cupboard");
        Location location = new Location("Warehouse 1");
        
        Item item = new Item("Plate", 8, type, location, true, false);
        item = repository.save(item); 
        assertThat(item.getItemId()).isNotNull(); }
}

