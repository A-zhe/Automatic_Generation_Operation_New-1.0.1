package fourth;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * �๦��������������������
 * 
 * @author�����ܡ�����Ȼ
 * 
 * @version�� 1.0.1(�������������쳣����������Χ�쳣�Ĵ������ָ����������Ź���)
 * 
 */

public class Arithmetic4_1 {

	/*
	 * �ж��Ƿ���Ҫ����
	 */
	public static int min = 0; // ������Сֵ
	public static int fl = 1; // ������Ŀ��ʼ���� k

	static int need_Negative(int flag, int max) {
		// �����Ҫ����������СֵΪ100
		if (flag == 1) {
			min = -max;
		}
		// int randNumber =rand.nextInt(MAX - MIN + 1) + MIN;������ֵΪһ�� MIN �� MAX
		// ��Χ�ڵ������
		return (max - min + 1);
	}

	/*
	 * �ж��Ƿ�����˷��ͳ���
	 */
	static char need_MulandDiv(int flag) {
		char x = 0;
		if (flag == 1) {
			char[] ch = { '+', '-', '*', '/' }; // �ַ�����
			Random r = new Random();
			int index = r.nextInt(ch.length); // �������С������ĳ�����, 0~3
			x = ch[index];// ��ӡ����ַ�
		} else if (flag == 0) {
			char[] ch = { '+', '-' }; // �ַ�����
			Random r = new Random();
			int index = r.nextInt(ch.length); // �������С������ĳ�����, 0~1
			x = ch[index];
		}
		return x;
	}

	/*
	 * ��ȡ�����
	 */
	static int get_num(int haveorno_fu) {
		Random auto_number = new Random();
		int a = auto_number.nextInt(haveorno_fu) + min;
		return a;
	}

	/*
	 * ��ȡ��ʽ����
	 */
	static float get_result(int nux, char op, int nuy) {

		float res = 0;

		if (op == '+') {
			res = nux + nuy;
			return res;
		} else if (op == '-') {
			res = nux - nuy;
			return res;
		} else if (op == '*') {
			res = nux * nuy;
			return res;
		} else if (op == '/') {
			return (float) Math.round(((float) nux / nuy) * 100) / 100;
		} else {
			return 0000;
		}
	}

	/*
	 * ����������
	 */
	static String get_minus(float numz) {
		if (numz < 0) {
			return "(" + numz + ")";
		}else {
			return numz + "";
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		try {

			Scanner scanner = new Scanner(System.in);
			System.out.println("������Ҫ���ɵ���ϰ��������");
			int expression_Count = scanner.nextInt();

			System.out.println("�������������Χ��������100����ʾ100���ڣ���");
			int max_toprange = scanner.nextInt();

			System.out.println("�������Ƿ���Ҫ������1�����ǣ�0����񣩣�");
			int zheng_fu = scanner.nextInt();
			int rand_num = need_Negative(zheng_fu, max_toprange);
			int haveorno_fu = rand_num;

			System.out.println("�������Ƿ�����˷��ͳ�����1�����ǣ�0����񣩣�");
			int cheng_chu = scanner.nextInt();

			int[] numx = new int[expression_Count + 1];
			char[] numc = new char[expression_Count];
			int[] numy = new int[expression_Count + 1];
			float[] numz = new float[expression_Count];

			int i = 0;

			while (fl <= expression_Count) {
				char operator = need_MulandDiv(cheng_chu);

				int a = get_num(haveorno_fu);
				int b = get_num(haveorno_fu);

				float resultz = get_result(a, operator, b);
				if (resultz <= max_toprange && resultz >= -max_toprange) {
					// �����ظ���ʽ
					if (i == 0) {// ��һ�����
						numx[i] = a;
						numc[i] = operator;
						numy[i] = b;
						numz[i] = get_result(a, operator, b);
						i++;
					} else {
						for (int j = 0; j < i; j++) {// �ڶ��⿪ʼ��֮ǰÿһ�����Ƚ�

							if (a != numx[j] || operator != numc[j] || b != numy[j]) {
								numx[i] = a;
								numc[i] = operator;
								numy[i] = b;
								numz[i] = get_result(a, operator, b);
							} else {
								fl--;// ������ظ����һ��
							}
						}
						i++;
					}
					fl++;
				}

			}

			/*
			 * �����ļ�
			 */
			File file = new File("E:\\result.txt");
			Writer out = new FileWriter(file);
			System.out.println("����txt�ļ���.............");
			for (int m = 0; m < fl - 1; m++) {
				out.write(get_minus(numx[m]) + " " + numc[m] + " " + get_minus(numy[m]) + "  =  \r\n");
			}
			System.out.println("�������................");
			out.close();

			/*
			 * ��ӡ��
			 */
			System.out.println("��ѡ���Ƿ��ӡ�𰸣�1�����ӡ��0������ӡ��");
			int yesno_print = scanner.nextInt();
			if (yesno_print == 1) {
				for (int m = 0; m < fl - 1; m++) {
					System.out.println(get_minus(numx[m]) + " " + numc[m] + " " + get_minus(numy[m]) + "  =  " + get_minus(numz[m]));
				}
			}
			System.out.println("��ӡ��ɡ�");

		} catch (InputMismatchException e) {
			System.err.println("�����������������Ҫ�����������");
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
}
