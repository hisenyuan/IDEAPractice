package com.hisen.api.douban.bean;

import java.util.List;

/**
 * Created by hisen on 17-9-8.
 * E-mail: hisenyuan@gmail.com
 */
public class Book {

  /**
   * 评分
   */
  private Rating rating;
  /**
   * 副标题
   */
  private String subtitle;
  /**
   * 作者
   */
  private List<String> author;
  /**
   * 出版日期
   */
  private String pubdate;
  /**
   * 标签
   */
  private List<Tag> tags;
  /**
   *
   */
  private String origin_title;
  /**
   * 封面图片
   */
  private String image;
  /**
   * 装帧
   */
  private String binding;
  /**
   * 翻译
   */
  private List<String> translator;
  /**
   * 目录
   */
  private String catalog;
  /**
   * 页数
   */
  private String pages;
  /**
   * 封面图片 - 大中小
   */
  private Images images;
  /**
   * 图书链接(豆瓣)
   */
  private String alt;
  /**
   * 图书id
   */
  private String id;
  /**
   * 出版社
   */
  private String publisher;
  private String isbn10;
  private String isbn13;
  /**
   * 标题
   */
  private String title;
  /**
   * API链接
   */
  private String url;

  private String alt_title;
  private String author_intro;
  /**
   * 简介
   */
  private String summary;
  /**
   * 价格
   */
  private String price;

  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public List<String> getAuthor() {
    return author;
  }

  public void setAuthor(List<String> author) {
    this.author = author;
  }

  public String getPubdate() {
    return pubdate;
  }

  public void setPubdate(String pubdate) {
    this.pubdate = pubdate;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public String getOrigin_title() {
    return origin_title;
  }

  public void setOrigin_title(String origin_title) {
    this.origin_title = origin_title;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getBinding() {
    return binding;
  }

  public void setBinding(String binding) {
    this.binding = binding;
  }

  public List<String> getTranslator() {
    return translator;
  }

  public void setTranslator(List<String> translator) {
    this.translator = translator;
  }

  public String getCatalog() {
    return catalog;
  }

  public void setCatalog(String catalog) {
    this.catalog = catalog;
  }

  public String getPages() {
    return pages;
  }

  public void setPages(String pages) {
    this.pages = pages;
  }

  public Images getImages() {
    return images;
  }

  public void setImages(Images images) {
    this.images = images;
  }

  public String getAlt() {
    return alt;
  }

  public void setAlt(String alt) {
    this.alt = alt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getIsbn10() {
    return isbn10;
  }

  public void setIsbn10(String isbn10) {
    this.isbn10 = isbn10;
  }

  public String getIsbn13() {
    return isbn13;
  }

  public void setIsbn13(String isbn13) {
    this.isbn13 = isbn13;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getAlt_title() {
    return alt_title;
  }

  public void setAlt_title(String alt_title) {
    this.alt_title = alt_title;
  }

  public String getAuthor_intro() {
    return author_intro;
  }

  public void setAuthor_intro(String author_intro) {
    this.author_intro = author_intro;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
