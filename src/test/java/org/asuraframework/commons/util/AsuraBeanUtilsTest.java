/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * <p></p>
 *
 * @author liusq23
 * @version 1.0
 * @Date 2018/3/17 下午9:52
 * @since 1.0
 */
public class AsuraBeanUtilsTest {

    /**
     * 测试 target 少属性情况
     */
    @Test
    public void testCopier() {
        AddressEntity entity = new AddressEntity();
        entity.setAddreddId(1);
        entity.setAddressName("address name");
        entity.setLongitude(101.12122f);
        AddressDto dto = AsuraBeanUtils.copyProperties(entity, AddressDto.class);
        Assert.assertNotNull(dto);
        Assert.assertThat(dto.getAddressName(), containsString("address"));
        //类型对不上的不拷贝
        Assert.assertNotEquals(101.12122f, dto.getLongitude());
        // target少属性不拷贝
        Assert.assertNull(dto.getLatitude());
    }

    @Test
    public void testCopierComplex() {
        UserEntity userEntity = new UserEntity();
        AddressEntity entity = new AddressEntity();
        entity.setAddreddId(1);
        entity.setAddressName("address name");
        entity.setLongitude(101.12122f);
        userEntity.setAddressEntity(entity);
        userEntity.setUserId(12);
        userEntity.setEmail("aa@gmail.com");
        userEntity.setName("user name");
        UserDto dto = AsuraBeanUtils.copyProperties(userEntity, UserDto.class);
        Assert.assertNotNull(dto);
        Assert.assertThat(dto.getEmail(), containsString("gmail"));
        //类型不匹配，为null
        Assert.assertNull(dto.getAddressDto());
    }

    /**
     * 测试 target 少属性情况
     */
    @Test
    public void testCopierInstance() {
        AddressEntity entity = new AddressEntity();
        entity.setAddreddId(1);
        entity.setAddressName("address name");
        entity.setLongitude(101.12122f);
        AddressDto dto = new AddressDto();
        AsuraBeanUtils.copyProperties(entity, dto);
        Assert.assertThat(dto.getAddressName(), containsString("address"));
        //类型对不上的不拷贝
        Assert.assertNotEquals(101.12122f, dto.getLongitude());
        // target少属性不拷贝
        Assert.assertNull(dto.getLatitude());
    }

    /**
     * 测试 target 少属性情况
     */
    @Test(expected = NullPointerException.class)
    public void testCopierList() {
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(12);
        userEntity.setEmail("aa@gmail.com");
        userEntity.setName("user name");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId(13);
        userEntity2.setEmail("ac@gmail.com");
        userEntity2.setName("user name2");
        userEntities.add(userEntity);
        userEntities.add(null);
        userEntities.add(userEntity2);
        AsuraBeanUtils.copyProperties(userEntities, UserDto.class);
    }

    @Test
    public void testCopierList2() {
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(12);
        userEntity.setEmail("aa@gmail.com");
        userEntity.setName("user name");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId(13);
        userEntity2.setEmail("bb@gmail.com");
        userEntity2.setName("user name2");
        userEntities.add(userEntity);
        userEntities.add(userEntity2);
        List<UserDto> userDtos = AsuraBeanUtils.copyProperties(userEntities, UserDto.class);
        Assert.assertEquals(userDtos.size(), 2);
        Assert.assertThat(userDtos.get(0).getEmail(), containsString("aa"));
        Assert.assertThat(userDtos.get(1).getEmail(), containsString("bb"));
        Assert.assertThat(userDtos.get(0).getName(), containsString("name"));
        Assert.assertThat(userDtos.get(1).getName(), containsString("name2"));
    }

    /**
     * 测试同类之间的嵌套类型复制
     */
    @Test
    public void testCopierList3() {
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(12);
        userEntity.setEmail("aa@gmail.com");
        userEntity.setName("user name");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId(13);
        userEntity2.setEmail("bb@gmail.com");
        userEntity2.setName("user name2");
        userEntities.add(userEntity);
        userEntities.add(userEntity2);
        AddressEntity entity = new AddressEntity();
        entity.setAddreddId(1);
        entity.setAddressName("address name");
        entity.setLongitude(101.12122f);
        userEntity.setAddressEntity(entity);
        List<UserEntity> userDtos = AsuraBeanUtils.copyProperties(userEntities, UserEntity.class);
        Assert.assertEquals(userDtos.size(), 2);
        Assert.assertThat(userDtos.get(0).getEmail(), containsString("aa"));
        Assert.assertThat(userDtos.get(0).getAddressEntity().getAddressName(), containsString("address"));
        Assert.assertThat(userDtos.get(1).getEmail(), containsString("bb"));
        Assert.assertThat(userDtos.get(0).getName(), containsString("name"));
        Assert.assertThat(userDtos.get(1).getName(), containsString("name2"));
    }


    @Test
    public void testCopierList4() {
        List<UserEntity> userEntities = new ArrayList<>();
        List<UserDto> userDtos = AsuraBeanUtils.copyProperties(userEntities, UserDto.class);
        List<UserDto> userDtos1 = AsuraBeanUtils.copyProperties(null, UserDto.class);
        Assert.assertEquals(userDtos.size(), 0);
        Assert.assertEquals(userDtos1.size(), 0);
    }


    @Test(expected = BeanUtilsCopyException.class)
    public void testCopierList5() {
        AddressEntity entity = new AddressEntity();
        entity.setAddreddId(1);
        entity.setAddressName("address name");
        entity.setLongitude(101.12122f);
        AsuraBeanUtils.copyProperties(entity, AddressDto2.class);
    }
}
