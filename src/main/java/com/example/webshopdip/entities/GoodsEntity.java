package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Клас, що представляє сутність "Товари".
 * Містить дані та поведінку, пов'язані з товаром.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "goods")
public class GoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Товару
    private String name; // Назва Товару
    private String short_discription; // Короткий опис Товару

    @OneToOne
    @JoinColumn(name = "descriptionsGoods_id", referencedColumnName = "id")
    // Зв'язок One-to-One: Один Товар має один Повний Опис товару
    private DescriptionsGoodsEntity descriptionsGoods;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "subcategoriesGoods_id", referencedColumnName = "id")
    // Зв'язок One-to-One: Багато Товарів можуть належати одній Підкатегорії
    private SubcategoriesGoodsEntity subcategoriesGoods;


    /////////сутності що мають відношення One-to-Many з сутністю Goods
    @OneToMany(mappedBy = "goods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один товар може належати до багатьох Переліків Товарів що надходять до магазину
    private List<GoodsInvoicesEntity> goodsInvoices = new ArrayList<>();
    @OneToMany(mappedBy = "goods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один товар може мати багато Знижок
    private List<DiscountsEntity> discounts = new ArrayList<>();
    @OneToMany(mappedBy = "goods")
    @JsonManagedReference
//    @JsonIgnoreProperties("goods")
    // Зв'язок One-to-Many: Один товар може мати багато Фотографій
    private List<PhotosGoodsEntity> photosGoods = new ArrayList<>();
    @OneToMany(mappedBy = "goods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один товар може мати багато Властивостей, Атрибутів
    private List<PropertiesGoodsEntity> propertiesGoods = new ArrayList<>();
    @OneToMany(mappedBy = "goods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один товар може мати багато Історій перегляду
    private List<HistoryViewsGoodsEntity> historyViewsGoods = new ArrayList<>();
    @OneToMany(mappedBy = "goods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Щодо одного товару можна ставити багато Питань
    private List<QuestionsEntity> questions = new ArrayList<>();
    @OneToMany(mappedBy = "goods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один товар може мати багато Коментарів
    private List<CommentsEntity> comments = new ArrayList<>();
    @OneToMany(mappedBy = "goods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один товар може мати багато Оцінок
    private List<EvaluationsGoodEntity> evaluationsGoods = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_discription() {
        return short_discription;
    }

    public void setShort_discription(String short_discription) {
        this.short_discription = short_discription;
    }

    public DescriptionsGoodsEntity getDescriptionsGoods() {
        return descriptionsGoods;
    }

    public void setDescriptionsGoods(DescriptionsGoodsEntity descriptionsGoods) {
        this.descriptionsGoods = descriptionsGoods;
    }

    public SubcategoriesGoodsEntity getSubcategoriesGoods() {
        return subcategoriesGoods;
    }

    public void setSubcategoriesGoods(SubcategoriesGoodsEntity subcategoriesGoods) {
        this.subcategoriesGoods = subcategoriesGoods;
    }

    public List<GoodsInvoicesEntity> getGoodsInvoices() {
        return goodsInvoices;
    }

    public void setGoodsInvoices(List<GoodsInvoicesEntity> goodsInvoices) {
        this.goodsInvoices = goodsInvoices;
    }

//    public List<GoodsOrdersEntity> getGoodsOrders() {
//        return goodsOrders;
//    }
//
//    public void setGoodsOrders(List<GoodsOrdersEntity> goodsOrders) {
//        this.goodsOrders = goodsOrders;
//    }

    public List<DiscountsEntity> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountsEntity> discounts) {
        this.discounts = discounts;
    }

    public List<PhotosGoodsEntity> getPhotosGoods() {
        return photosGoods;
    }

    public void setPhotosGoods(List<PhotosGoodsEntity> photosGoods) {
        this.photosGoods = photosGoods;
    }

    public List<PropertiesGoodsEntity> getPropertiesGoods() {
        return propertiesGoods;
    }

    public void setPropertiesGoods(List<PropertiesGoodsEntity> propertiesGoods) {
        this.propertiesGoods = propertiesGoods;
    }

    public List<HistoryViewsGoodsEntity> getHistoryViewsGoods() {
        return historyViewsGoods;
    }

    public void setHistoryViewsGoods(List<HistoryViewsGoodsEntity> historyViewsGoods) {
        this.historyViewsGoods = historyViewsGoods;
    }

    public List<QuestionsEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsEntity> questions) {
        this.questions = questions;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

    public List<EvaluationsGoodEntity> getEvaluationsGoods() {
        return evaluationsGoods;
    }

    public void setEvaluationsGoods(List<EvaluationsGoodEntity> evaluationsGoods) {
        this.evaluationsGoods = evaluationsGoods;
    }
}
