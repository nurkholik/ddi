package com.github.nurkholik.ddi;

import com.github.nurkholik.ddi.repository.CategoryRepository;
import com.github.nurkholik.ddi.repository.GroupRepository;
import com.github.nurkholik.ddi.repository.model.Category;
import com.github.nurkholik.ddi.repository.model.Groupx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    CategoryRepository categoryRepository;

    static final String dummyImage = "https://media-exp2.licdn.com/dms/image/C510BAQE67gwb3umYwg/company-logo_200_200/0/1519903004318?e=2147483647&v=beta&t=rswTDYXDpiFZjUe1-t2moNyNQBkcGcrfxuSRljAsdSs";
    static final String dummyDetail = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // Insert Category
        categoryRepository.save(new Category(1, "Company", dummyImage, 1));
        categoryRepository.save(new Category(2, "Community", dummyImage, 2));
        categoryRepository.save(new Category(3, "Education", dummyImage, 3));

        // Insert group;
        for (int i=1; i<=3; i++) {
            for (int cat=1; cat<100; cat++) {
                Category mCat = categoryRepository.findById(i).orElseThrow(() -> new RuntimeException("Failed"));
                groupRepository.save(new Groupx(i*100+cat, i, mCat.getName() + " " + cat, dummyDetail, dummyImage));
            }
        }
    }
}
