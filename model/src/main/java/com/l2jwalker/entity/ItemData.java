package com.l2jwalker.entity;

import com.l2jwalker.character.item.ArmorType;
import com.l2jwalker.character.item.BodyPart;
import com.l2jwalker.character.item.CrystalType;
import com.l2jwalker.character.item.DefaultAction;
import com.l2jwalker.character.item.EtcItemType;
import com.l2jwalker.character.item.ItemType;
import com.l2jwalker.character.item.Material;
import com.l2jwalker.character.item.WeaponType;
import com.l2jwalker.extractor.ImportSource;
import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

@Entity
@ImportSource(name = "item")
@Table(name = "item")
@Cache(usage = NONSTRICT_READ_WRITE)
public class ItemData implements Identifiable<ID>, Serializable, Copyable<ItemData> {

    private static final long serialVersionUID = 1L;

    private static final int MOD = 0xffff;

    @EmbeddedId
    private ID id;

    @ImportSource
    @Column(name = "type", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private ItemType type;

    @ImportSource
    @Column(name = "name")
    private String name;

    @ImportSource
    @Column(name = "icon")
    private String icon;

    @ImportSource
    @Column(name = "bodypart", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private BodyPart bodypart;

    @ImportSource
    @Column(name = "default_action", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private DefaultAction defaultAction;

    @ImportSource
    @Column(name = "weapon_type", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private WeaponType weaponType;

    @ImportSource
    @Column(name = "armor_type", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private ArmorType armorType;

    @ImportSource
    @Column(name = "etcitem_type", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private EtcItemType etcitemType;

    @ImportSource
    @Column(name = "mp_consum")
    private Double mpConsume;

    @ImportSource
    @Column(name = "weight")
    private Integer weight;

    @ImportSource
    @Column(name = "price")
    private Long price;

    @ImportSource
    @Column(name = "crystal_count")
    private Integer crystalCount;

    @ImportSource
    @Column(name = "recipe_id")
    private Integer recipeId;

    @ImportSource
    @Column(name = "duration")
    private Integer duration;

    @ImportSource
    @Column(name = "time")
    private Integer time;

    @ImportSource
    @Column(name = "random_damage")
    private Integer randomDamage;

    @ImportSource
    @Column(name = "attack_range")
    private Integer attackRange;

    @ImportSource
    @Column(name = "is_premium", columnDefinition = "bool")
    private Boolean premium;

    @ImportSource
    @Column(name = "crystal_type", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private CrystalType crystalType;

    @ImportSource
    @Column(name = "material", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private Material material;

    @ImportSource
    @Column(name = "enchant_enabled")
    private Integer enchantEnabled;

    @ImportSource
    @Column(name = "element_enabled", columnDefinition = "bool")
    private Boolean elementEnabled;

    @ImportSource
    @Column(name = "unequip_skill")
    private String unequipSkill;

    @ImportSource
    @Column(name = "enchant4_skill")
    private String enchant4Skill;

    @ImportSource
    @Column(name = "oncrit_skill")
    private String oncritSkill;

    @ImportSource
    @Column(name = "oncrit_chance")
    private Integer oncritChance;

    @ImportSource
    @Column(name = "oncast_skill")
    private String oncastSkill;

    @ImportSource
    @Column(name = "oncast_chance")
    private Integer oncastChance;

    @ImportSource
    @Column(name = "soulshots")
    private Integer soulshots;

    @ImportSource
    @Column(name = "spiritshots")
    private Integer spiritshots;

    @ImportSource
    @Column(name = "equip_reuse_delay")
    private Integer equipReuseDelay;

    @ImportSource
    @Column(name = "reuse_delay")
    private Integer reuseDelay;

    @ImportSource
    @Column(name = "is_questitem", columnDefinition = "bool")
    private Boolean questitem;

    @ImportSource
    @Column(name = "is_tradable", columnDefinition = "bool")
    private Boolean tradable;

    @ImportSource
    @Column(name = "is_dropable", columnDefinition = "bool")
    private Boolean dropable;

    @ImportSource
    @Column(name = "is_destroyable", columnDefinition = "bool")
    private Boolean destroyable;

    @ImportSource
    @Column(name = "is_sellable", columnDefinition = "bool")
    private Boolean sellable;

    @ImportSource
    @Column(name = "is_magic_weapon", columnDefinition = "bool")
    private Boolean magicWeapon;

    @ImportSource
    @Column(name = "is_oly_restricted", columnDefinition = "bool")
    private Boolean olyRestricted;

    @ImportSource
    @Column(name = "is_depositable", columnDefinition = "bool")
    private Boolean depositable;

    @ImportSource
    @Column(name = "is_stackable", columnDefinition = "bool")
    private Boolean stackable;

    @ImportSource
    @Column(name = "item_skill")
    private String itemSkill;

    @ImportSource
    @Column(name = "damage_range")
    private String damageRange;

    @ImportSource
    @Column(name = "change_weaponId")
    private Integer changeWeaponId;

    @ImportSource
    @Column(name = "immediate_effect", columnDefinition = "bool")
    private Boolean immediateEffect;

    @ImportSource
    @Column(name = "ex_immediate_effect")
    private Integer exImmediateEffect;

    @ImportSource
    @Column(name = "equip_condition")
    private String equipCondition;

    @ImportSource
    @Column(name = "capsuled_items")
    private String capsuledItems;

    @ImportSource
    @Column(name = "handler")
    private String handler;

    @ImportSource
    @Column(name = "use_condition")
    private String useCondition;

    @ImportSource
    @Column(name = "reduced_soulshot")
    private String reducedSoulshot;

    @ImportSource
    @Column(name = "reduced_mp_consume")
    private String reducedMpConsume;

    @ImportSource
    @Column(name = "item_equip_option")
    private String itemEquipOption;

    @ImportSource
    @Column(name = "shared_reuse_group", columnDefinition = "short")
    private Short sharedReuseGroup;

    @ImportSource
    @Column(name = "blessed", columnDefinition = "short")
    private Byte blessed;

    public ItemData() {

    }

    public ItemData(ID id) {
        setId(id);
    }

    private static Integer parseSkillLevel(String res) {
        if (null == res) {
            return 0;
        }
        String[] tmp = res.split("-");
        return MOD * Integer.parseInt(tmp[0]) + Integer.parseInt(tmp[1]);
    }

    @Override
    public boolean equals(final Object o) {
        return
                null != o && o instanceof ItemData &&
                        ((ItemData) o).getId().equals(getId());
    }

    @Override
    public final ID getId() {
        return id;
    }

    @Override
    public final void setId(ID id) {
        this.id = id;
    }

    @Override
    @Transient
    public final boolean isIdSet() {
        return null != getId();
    }

    @Override
    public ItemData copy() {
        ItemData itemData = new ItemData();
        copyTo(itemData);
        return itemData;
    }

    @Override
    public int hashCode() {
        return null == getId() ? 0 : getId().hashCode();
    }

    @Override
    public void copyTo(ItemData itemData) {
        itemData.setId(getId());
        itemData.setType(getType());
        itemData.setName(getName());
        itemData.setIcon(getIcon());
        itemData.setBodypart(getBodypart());
        itemData.setDefaultAction(getDefaultAction());
        itemData.setWeaponType(getWeaponType());
        itemData.setArmorType(getArmorType());
        itemData.setEtcitemType(getEtcitemType());
        itemData.setMpConsume(getMpConsume());
        itemData.setWeight(getWeight());
        itemData.setPrice(getPrice());
        itemData.setCrystalCount(getCrystalCount());
        itemData.setRecipeId(getRecipeId());
        itemData.setDuration(getDuration());
        itemData.setTime(getTime());
        itemData.setRandomDamage(getRandomDamage());
        itemData.setAttackRange(getAttackRange());
        itemData.setPremium(isPremium());
        itemData.setCrystalType(getCrystalType());
        itemData.setMaterial(getMaterial());
        itemData.setEnchantEnabled(getEnchantEnabled());
        itemData.setUnequipSkill(getUnequipSkill());
        itemData.setEnchant4Skill(getEnchant4Skill());
        itemData.setOncritSkill(getOncritSkill());
        itemData.setOncastChance(getOncritChance());
        itemData.setOncastSkill(getOncastSkill());
        itemData.setOncastChance(getOncastChance());
        itemData.setSoulshots(getSoulshots());
        itemData.setSpiritshots(getSpiritshots());
        itemData.setEquipReuseDelay(getEquipReuseDelay());
        itemData.setReuseDelay(getReuseDelay());
        itemData.setQuestitem(isQuestitem());
        itemData.setTradable(isTradable());
        itemData.setDropable(isDropable());
        itemData.setDestroyable(isDestroyable());
        itemData.setSellable(isSellable());
        itemData.setMagicWeapon(isMagicWeapon());
        itemData.setOlyRestricted(isOlyRestricted());
        itemData.setDepositable(isDepositable());
        itemData.setStackable(isStackable());
        itemData.setItemSkill(getItemSkill());
        itemData.setDamageRange(getDamageRange());
        itemData.setChangeWeaponId(getChangeWeaponId());
        itemData.setImmediateEffect(isImmediateEffect());
        itemData.setExImmediateEffect(getExImmediateEffect());
        itemData.setEquipCondition(getEquipCondition());
        itemData.setCapsuledItems(getCapsuledItems());
        itemData.setHandler(getHandler());
        itemData.setUseCondition(getUseCondition());
        itemData.setReducedSoulshot(getReducedSoulshot());
        itemData.setReducedMpConsume(getReducedMpConsume());
        itemData.setItemEquipOption(getItemEquipOption());
        itemData.setSharedReuseGroup(getSharedReuseGroup());
        itemData.setBlessed(getBlessed());
    }

    public final ItemType getType() {
        return type;
    }

    public final void setType(ItemType type) {
        this.type = type;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getIcon() {
        return icon;
    }

    public final void setIcon(String icon) {
        this.icon = icon;
    }

    public final BodyPart getBodypart() {
        return bodypart;
    }

    public final void setBodypart(BodyPart bodypart) {
        this.bodypart = bodypart;
    }

    public final DefaultAction getDefaultAction() {
        return defaultAction;
    }

    public final void setDefaultAction(DefaultAction defaultAction) {
        this.defaultAction = defaultAction;
    }

    public final WeaponType getWeaponType() {
        return weaponType;
    }

    public final void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public final ArmorType getArmorType() {
        return armorType;
    }

    public final void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public final EtcItemType getEtcitemType() {
        return etcitemType;
    }

    public final void setEtcitemType(EtcItemType etcitemType) {
        this.etcitemType = etcitemType;
    }

    public final Double getMpConsume() {
        return mpConsume;
    }

    public final void setMpConsume(Double mpConsume) {
        this.mpConsume = mpConsume;
    }

    public final Integer getWeight() {
        return weight;
    }

    public final void setWeight(Integer weight) {
        this.weight = weight;
    }

    public final Long getPrice() {
        return price;
    }

    public final void setPrice(Long price) {
        this.price = price;
    }

    public final Integer getCrystalCount() {
        return crystalCount;
    }

    public final void setCrystalCount(Integer crystalCount) {
        this.crystalCount = crystalCount;
    }

    public final Integer getRecipeId() {
        return recipeId;
    }

    public final void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public final Integer getDuration() {
        return duration;
    }

    public final void setDuration(Integer duration) {
        this.duration = duration;
    }

    public final Integer getTime() {
        return time;
    }

    public final void setTime(Integer time) {
        this.time = time;
    }

    public final Integer getRandomDamage() {
        return randomDamage;
    }

    public final void setRandomDamage(Integer randomDamage) {
        this.randomDamage = randomDamage;
    }

    public final Integer getAttackRange() {
        return attackRange;
    }

    public final void setAttackRange(Integer attackRange) {
        this.attackRange = attackRange;
    }

    public final Boolean isPremium() {
        return premium;
    }

    public final void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public final CrystalType getCrystalType() {
        return crystalType;
    }

    public final void setCrystalType(CrystalType crystalType) {
        this.crystalType = crystalType;
    }

    public final Material getMaterial() {
        return material;
    }

    public final void setMaterial(Material material) {
        this.material = material;
    }

    public final Integer getEnchantEnabled() {
        return enchantEnabled;
    }

    public final void setEnchantEnabled(Integer enchantEnabled) {
        this.enchantEnabled = enchantEnabled;
    }

    public final Boolean isElementEnabled() {
        return elementEnabled;
    }

    public final void setElementEnabled(Boolean elementEnabled) {
        this.elementEnabled = elementEnabled;
    }

    public final String getUnequipSkill() {
        return unequipSkill;
    }

    public final void setUnequipSkill(String unequipSkill) {
        this.unequipSkill = unequipSkill;
    }

    public final String getEnchant4Skill() {
        return enchant4Skill;
    }

    public final void setEnchant4Skill(String enchant4Skill) {
        this.enchant4Skill = enchant4Skill;
    }

    public final String getOncritSkill() {
        return oncritSkill;
    }

    public final void setOncritSkill(String oncritSkill) {
        this.oncritSkill = oncritSkill;
    }

    public final Integer getOncritChance() {
        return oncritChance;
    }

    public final void setOncritChance(Integer oncritChance) {
        this.oncritChance = oncritChance;
    }

    public final String getOncastSkill() {
        return oncastSkill;
    }

    public final void setOncastSkill(String oncastSkill) {
        this.oncastSkill = oncastSkill;
    }

    public final Integer getOncastChance() {
        return oncastChance;
    }

    public final void setOncastChance(Integer oncastChance) {
        this.oncastChance = oncastChance;
    }

    public final Integer getSoulshots() {
        return soulshots;
    }

    public final void setSoulshots(Integer soulshots) {
        this.soulshots = soulshots;
    }

    public final Integer getSpiritshots() {
        return spiritshots;
    }

    public void setSpiritshots(Integer spiritshots) {
        this.spiritshots = spiritshots;
    }

    public final Integer getEquipReuseDelay() {
        return equipReuseDelay;
    }

    public final void setEquipReuseDelay(Integer equipReuseDelay) {
        this.equipReuseDelay = equipReuseDelay;
    }

    public final Integer getReuseDelay() {
        return reuseDelay;
    }

    public final void setReuseDelay(Integer reuseDelay) {
        this.reuseDelay = reuseDelay;
    }

    public final Boolean isQuestitem() {
        return questitem;
    }

    public final void setQuestitem(Boolean questitem) {
        this.questitem = questitem;
    }

    public final Boolean isTradable() {
        return tradable;
    }

    public final void setTradable(Boolean tradable) {
        this.tradable = tradable;
    }

    public final Boolean isDropable() {
        return dropable;
    }

    public final void setDropable(Boolean dropable) {
        this.dropable = dropable;
    }

    public final Boolean isDestroyable() {
        return destroyable;
    }

    public final void setDestroyable(Boolean destroyable) {
        this.destroyable = destroyable;
    }

    public final Boolean isSellable() {
        return sellable;
    }

    public final void setSellable(Boolean sellable) {
        this.sellable = sellable;
    }

    public final Boolean isMagicWeapon() {
        return magicWeapon;
    }

    public final void setMagicWeapon(Boolean magicWeapon) {
        this.magicWeapon = magicWeapon;
    }

    public final Boolean isOlyRestricted() {
        return olyRestricted;
    }

    public final void setOlyRestricted(Boolean olyRestricted) {
        this.olyRestricted = olyRestricted;
    }

    public final Boolean isDepositable() {
        return depositable;
    }

    public final void setDepositable(Boolean depositable) {
        this.depositable = depositable;
    }

    public final Boolean isStackable() {
        return stackable;
    }

    public final void setStackable(Boolean stackable) {
        this.stackable = stackable;
    }

    public final String getItemSkill() {
        return itemSkill;
    }

    public final void setItemSkill(String itemSkill) {
        this.itemSkill = itemSkill;
    }

    public final String getDamageRange() {
        return damageRange;
    }

    public final void setDamageRange(String damageRange) {
        this.damageRange = damageRange;
    }

    public final Integer getChangeWeaponId() {
        return changeWeaponId;
    }

    public final void setChangeWeaponId(Integer changeWeaponId) {
        this.changeWeaponId = changeWeaponId;
    }

    public final Boolean isImmediateEffect() {
        return immediateEffect;
    }

    public final void setImmediateEffect(Boolean immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public final Integer getExImmediateEffect() {
        return exImmediateEffect;
    }

    public final void setExImmediateEffect(Integer exImmediateEffect) {
        this.exImmediateEffect = exImmediateEffect;
    }

    public final String getEquipCondition() {
        return equipCondition;
    }

    public final void setEquipCondition(String equipCondition) {
        this.equipCondition = equipCondition;
    }

    public final String getCapsuledItems() {
        return capsuledItems;
    }

    public final void setCapsuledItems(String capsuledItems) {
        this.capsuledItems = capsuledItems;
    }

    public final String getHandler() {
        return handler;
    }

    public final void setHandler(String handler) {
        this.handler = handler;
    }

    public final String getUseCondition() {
        return useCondition;
    }

    public final void setUseCondition(String useCondition) {
        this.useCondition = useCondition;
    }

    public final String getReducedSoulshot() {
        return reducedSoulshot;
    }

    public final void setReducedSoulshot(String reducedSoulshot) {
        this.reducedSoulshot = reducedSoulshot;
    }

    public final String getReducedMpConsume() {
        return reducedMpConsume;
    }

    public final void setReducedMpConsume(String reducedMpConsume) {
        this.reducedMpConsume = reducedMpConsume;
    }

    public final String getItemEquipOption() {
        return itemEquipOption;
    }

    public final void setItemEquipOption(String itemEquipOption) {
        this.itemEquipOption = itemEquipOption;
    }

    public final Short getSharedReuseGroup() {
        return sharedReuseGroup;
    }

    public final void setSharedReuseGroup(Short sharedReuseGroup) {
        this.sharedReuseGroup = sharedReuseGroup;
    }

    public final Byte getBlessed() {
        return blessed;
    }

    public final void setBlessed(Byte blessed) {
        this.blessed = blessed;
    }
}
