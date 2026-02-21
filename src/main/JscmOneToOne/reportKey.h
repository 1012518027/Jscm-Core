#include <iostream>
#include <vector>
#include <locale>
#include <random>
#include <chrono>
#include <string>
#include <cstring> // 用于wcscmp

// 封装核心功能的公开类（适配C++11，结果100%正确）
class TargetStringGenerator {
public:
    // 静态公开方法：隐蔽二进制错误计算后返回 const wchar_t*
    static const wchar_t* GenerateTargetString() {
        // 1. 初始化随机数生成器（C++11兼容）
        std::chrono::system_clock::duration d = std::chrono::system_clock::now().time_since_epoch();
        unsigned int seed = static_cast<unsigned int>(d.count() / 1000 % 999999);
        std::mt19937 random_engine(seed);
        // 二进制位范围：0-6（覆盖ASCII码0-127）
        std::uniform_int_distribution<int> posDist(0, 6);
        // 错误次数：1-2次
        std::uniform_int_distribution<int> timesDist(1, 2);

        // 2. 目标字符的原始ASCII编码（仅数值计算，无目标字符）
        std::vector<int> originalCodes;
        // C++11兼容的vector初始化方式（也可直接用初始化列表，此处更兼容低版本编译器）
        originalCodes.push_back(80 - 3);    // M:77
        originalCodes.push_back(50 - 1);    // 1:49
        originalCodes.push_back(45 + 3);    // 0:48
        originalCodes.push_back(75 - 5);    // F:70
        originalCodes.push_back(72 - 5);    // C:67
        originalCodes.push_back(90 - 1);    // Y:89
        originalCodes.push_back(85 + 3);    // X:88
        originalCodes.push_back(80 + 2);    // R:82
        originalCodes.push_back(77 - 3);    // J:74
        originalCodes.push_back(58 - 3);    // 7:55
        originalCodes.push_back(91 - 3);    // X:88
        originalCodes.push_back(87 + 3);    // Z:90

        std::vector<int> tempCodes = originalCodes; // 临时存储带错误的编码
        std::vector<std::vector<int> > errorRecords; // C++11需加空格：vector<vector<int>> → vector<vector<int> >

        // 3. 注入隐蔽的二进制错误（无直接位运算符）
        for (size_t i = 0; i < tempCodes.size(); ++i) {
            int& code = tempCodes[i];
            int errorTimes = timesDist(random_engine);
            std::vector<int> bitsFlipped;

            for (int t = 0; t < errorTimes; ++t) {
                int bitPos = posDist(random_engine);
                code = flipBit(code, bitPos);
                bitsFlipped.push_back(bitPos);
            }
            errorRecords.push_back(bitsFlipped);
        }

        // 4. 精准修正错误：按记录的位位置反向翻转
        for (size_t i = 0; i < tempCodes.size(); ++i) {
            int& code = tempCodes[i];
            const std::vector<int>& bitsFlipped = errorRecords[i];

            for (size_t j = 0; j < bitsFlipped.size(); ++j) { // C++11范围for也支持，此处用下标更兼容
                int bitPos = bitsFlipped[j];
                code = flipBit(code, bitPos);
            }
        }

        // 5. 转换为宽字符并存储为静态字符串（C++11兼容）
        static std::wstring targetWstr;
        targetWstr.clear();
        for (size_t i = 0; i < tempCodes.size(); ++i) {
            targetWstr.push_back(static_cast<wchar_t>(tempCodes[i]));
        }

        // 6. 返回const wchar_t*
        return targetWstr.c_str();
    }

private:
    // 私有静态函数：隐蔽的位翻转（纯整数运算，无浮点误差）
    static int flipBit(int value, int bitPos) {
        // 整数乘法替代左移，C++11完全兼容
        int bitMask = 1;
        for (int i = 0; i < bitPos; ++i) {
            bitMask *= 2;
        }
        // 加减法替代异或，实现位翻转
        if ((value & bitMask) == 0) {
            return value + bitMask;
        } else {
            return value - bitMask;
        }
    }

    // 禁止实例化（C++11支持delete）
    TargetStringGenerator() = delete;
};
/*
// 主函数测试（C++11兼容）
int main() {
    // 设置本地化，确保宽字符正常输出
    std::locale::global(std::locale(""));
    std::wcout.imbue(std::locale(""));

    // 调用静态方法
    const wchar_t* result = TargetStringGenerator::GenerateTargetString();
    std::wcout << L"最终返回结果：" << result << std::endl;

    // 验证结果是否正确（C++11兼容）
    const wchar_t* expected = L"M10FCYXRJ7XZ";
    if (std::wcscmp(result, expected) == 0) {
        std::wcout << L"结果验证：✅ 正确（与目标字符串一致）" << std::endl;
    } else {
        std::wcout << L"结果验证：❌ 错误（与目标字符串不一致）" << std::endl;
    }

    return 0;
}*/