package com.scm.all.export;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 获取类中重写的方法名（返回拼接后的字符串）
 */
public class OverrideMethodUtils {

    /**
     * 获取指定类中所有重写的方法名，返回逗号分隔的字符串
     * @param clazz 目标类
     * @return 重写方法名拼接的字符串（无重写则返回空字符串）
     */
    public static String getOverrideMethodNamesStr(Class<?> clazz) {
        if (clazz == null || clazz.isInterface() || clazz.isPrimitive()) {
            return "";
        }

        // 存储重写方法名（LinkedHashSet保证有序+去重）
        Set<String> overrideMethodNames = new LinkedHashSet<>();
        Method[] currentMethods = clazz.getDeclaredMethods();

        for (Method currentMethod : currentMethods) {
            // 排除无法被重写的方法
            if (Modifier.isStatic(currentMethod.getModifiers())
                    || Modifier.isFinal(currentMethod.getModifiers())
                    || Modifier.isPrivate(currentMethod.getModifiers())
                    || currentMethod.isSynthetic()
                    || currentMethod.isBridge()) {
                continue;
            }

            // 检查是否重写父类/接口方法
            if (isOverrideSuperClassMethod(currentMethod, clazz)
                    || isOverrideInterfaceMethod(currentMethod, clazz)) {
                overrideMethodNames.add(currentMethod.getName());
            }
        }

        // 拼接为逗号分隔的字符串
        return String.join(",", overrideMethodNames);
    }

    // 判断是否重写父类方法
    private static boolean isOverrideSuperClassMethod(Method currentMethod, Class<?> clazz) {
        Class<?> superClass = clazz.getSuperclass();
        while (superClass != null && superClass != Object.class) {
            try {
                Method superMethod = superClass.getDeclaredMethod(
                        currentMethod.getName(), currentMethod.getParameterTypes()
                );
                // 校验返回值和访问权限兼容
                return isReturnCompatible(currentMethod, superMethod)
                        && isAccessModifierCompatible(currentMethod, superMethod);
            } catch (NoSuchMethodException e) {
                superClass = superClass.getSuperclass();
            }
        }
        // 额外检查是否重写Object类的方法（如toString、equals）
        try {
            Method objectMethod = Object.class.getDeclaredMethod(
                    currentMethod.getName(), currentMethod.getParameterTypes()
            );
            return isReturnCompatible(currentMethod, objectMethod)
                    && isAccessModifierCompatible(currentMethod, objectMethod);
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    // 判断是否重写接口方法
    private static boolean isOverrideInterfaceMethod(Method currentMethod, Class<?> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> iface : interfaces) {
            try {
                Method interfaceMethod = iface.getDeclaredMethod(
                        currentMethod.getName(), currentMethod.getParameterTypes()
                );
                // 接口方法默认public，子类必须为public
                return Modifier.isPublic(currentMethod.getModifiers());
            } catch (NoSuchMethodException e) {
                // 递归检查父接口
                if (isOverrideInterfaceMethod(currentMethod, iface)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 校验返回值兼容（协变返回）
    private static boolean isReturnCompatible(Method subMethod, Method superMethod) {
        return superMethod.getReturnType().isAssignableFrom(subMethod.getReturnType());
    }

    // 校验访问权限兼容
    private static boolean isAccessModifierCompatible(Method subMethod, Method superMethod) {
        int subMod = subMethod.getModifiers();
        int superMod = superMethod.getModifiers();

        if (Modifier.isPrivate(superMod)) return false;
        if (Modifier.isPublic(superMod)) return Modifier.isPublic(subMod);
        if (Modifier.isProtected(superMod)) {
            return Modifier.isPublic(subMod) || Modifier.isProtected(subMod);
        }
        return !Modifier.isPrivate(subMod); // 父类默认权限
    }

}
