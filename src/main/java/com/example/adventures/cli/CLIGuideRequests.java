package com.example.adventures.cli;

import com.example.adventures.appcontroller.BookTripController;
import com.example.adventures.bean.GuideBean;
import com.example.adventures.bean.RequestBean;
import com.example.adventures.engineering.Session;
import com.example.adventures.exception.NotFoundException;
import com.example.adventures.utilities.CLIPrinter;

import java.util.List;

import static com.example.adventures.dao.RequestDAO.acceptRequest;
import static com.example.adventures.dao.RequestDAO.declineRequest;

public class CLIGuideRequests extends AbstractCLI{



    public void start() throws NotFoundException {
        Session session = Session.getCurrentSession();
        if (session == null || session.getGuideBean() == null) {
            CLIPrinter.printMessage("Error: Guide session not found.");
            return;
        }

        GuideBean guideBean = session.getGuideBean();
        List<RequestBean> requestBeanList = getRequestsForGuide(guideBean);
        showMenuRequests(requestBeanList);
    }

    private List<RequestBean> getRequestsForGuide(GuideBean guideBean) throws NotFoundException {
        try {
            BookTripController bookTripController = new BookTripController();
            return bookTripController.listRequestGuide(guideBean);
        } catch (Exception e) {
            throw new NotFoundException("Failed to retrieve requests for this guide " );
        }
    }

    private void showMenuRequests(List<RequestBean> requestBeans) throws NotFoundException {
        boolean loop = true;

        while (loop) {
            CLIPrinter.printMessage("\n**************************** Select a Request ****************************\n");

            int i = 1;
            for (RequestBean requestBean : requestBeans) {
                CLIPrinter.printMessage(i + ") Trip: " + requestBean.getTripName() + "\n");
                CLIPrinter.printMessage("   Traveler: " + requestBean.getTravelerName() + " " + requestBean.getTravelerSurname() + "\n");
                CLIPrinter.printMessage("---------------------------------------------\n");
                i++;
            }

            CLIPrinter.printMessage("**************************************************************************\n");
            CLIPrinter.printMessage("Other options:\n");
            CLIPrinter.printMessage(i + ") Go home\n");
            CLIPrinter.printMessage((i + 1) + ") Logout\n\n");

            int choice = getMenuChoice(1, i + 1);

            if (choice <= requestBeans.size()) {
                handleRequest(requestBeans.get(choice - 1));
            } else if (choice == requestBeans.size() + 1) {
                goHome();
                loop = false; // Go home
            } else if (choice == requestBeans.size() + 2) {
                System.exit(0); // Logout
            } else {
                CLIPrinter.printMessage("Invalid choice. Please try again.\n");
            }
        }
    }

    private void handleRequest(RequestBean requestBean) throws NotFoundException {
        CLIPrinter.printMessage("\nHandling request for trip: " + requestBean.getTripName() + "\n");
        CLIPrinter.printMessage("1) Accept request\n");
        CLIPrinter.printMessage("2) Decline request\n");
        CLIPrinter.printMessage("3) Go home\n");

        int choice = getMenuChoice(1, 3);

        switch (choice) {
            case 1 -> {
                acceptRequest(requestBean);
                CLIPrinter.printMessage("\n -----------------\n");
                CLIPrinter.printMessage("|Request accepted!|\n");
                CLIPrinter.printMessage(" -----------------\n");
                goHome();
            }
            case 2 -> {
                declineRequest(requestBean);
                CLIPrinter.printMessage("\n ----------------\n");
                CLIPrinter.printMessage("|Request rejected|\n");
                CLIPrinter.printMessage(" ----------------\n");
                goHome();
            }
            case 3 -> goHome();
            default -> CLIPrinter.printMessage("Invalid choice.\n");
        }
    }
}
