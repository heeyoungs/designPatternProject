package library;

import exception.InputException;
import library.ui.CustomerUi;
import library.ui.EtcUi;
import library.ui.JoinUi;
import library.ui.OwnerUi;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws InputException {
        Scanner scanner = new Scanner(System.in);

        CustomerUi customerUi = new CustomerUi();
        OwnerUi ownerUi = new OwnerUi();
        JoinUi joinUi = new JoinUi();
        EtcUi etcUi = new EtcUi();

        int who;
        boolean run = true;

        while (run) {
            etcUi.startUi();

            try {
                who = scanner.nextInt();
            }catch (Exception e){
                throw new InputException("숫자를 입력안함.");
            }

            // switch 문 enum 타입으로 바꾸기
            switch (who) {
                case 0: {
                    etcUi.exitUi();
                    return;
                }
                case 1: {
                    ownerUi.run();
                    break;
                }
                case 2: {
                    customerUi.run();
                    break;
                }
                case 3:{
                    joinUi.run();
                    break;
                }
                default: {
                    etcUi.exceptionUi();
                    break;
                }
            }
        }
    }
}