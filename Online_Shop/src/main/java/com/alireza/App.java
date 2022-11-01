package com.alireza;

import com.alireza.model.Categories;
import com.alireza.model.Product;
import com.alireza.model.User;
import com.alireza.model.enumeration.Role;
import com.alireza.service.MainService;

import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MainService mainService = new MainService();

        boolean flag = true;

        while (flag) {
            try {
                printMainMenu();
                System.out.print("Choose an option: ");
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        register(mainService);
                        break;
                    case 2:
                        login(mainService);
                        break;
                    case 3:
                        flag = false;
                        break;
                    default:
                        System.out.println("You entered the wrong option!");
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println("invalid option");
                flag = false;
            }
        }
    }

    public static void printMainMenu() {
        System.out.println(
                "――――― Welcome To The Online Shop ――――――\n " +
                        "✎ 1. Register\n " +
                        "✎ 2. Login\n " +
                        "✎ 3. Exit Menu");
    }

    public static void printUserMenu() {
        System.out.println(
                "――――― Welcome To The User Page ――――――\n " +
                        "✎ 1. Add Product To Cart\n " +
                        "✎ 2. View Cart Details\n " +
                        "✎ 3. Edit Cart\n " +
                        "✎ 4. Final Confirmation Of Purchase\n " +
                        "✎ 5. Exit Menu");
    }

    public static void printEditCartMenu() {
        System.out.println(
                "――――― Welcome To The Edit Cart Page ――――――\n " +
                        "✎ 1. Add Product To Cart\n " +
                        "✎ 2. Delete Product From Cart\n " +
                        "✎ 3. Exit Menu");
    }

    public static void printAdminMenu() {
        System.out.println(
                "――――― Welcome To The Admin Page ――――――\n " +
                        "✎ 1. Edit Product\n " +
                        "✎ 2. Edit Category\n " +
                        "✎ 3. Exit Menu");
    }

    public static void printEditProductMenu() {
        System.out.println(
                "――――― Welcome To The Edit Product Page ――――――\n " +
                        "✎ 1. Add Product\n " +
                        "✎ 2. Edit Product Detail\n " +
                        "✎ 3. Delete Product \n " +
                        "✎ 4. Exit Menu");
    }

    public static void printEditCategoryMenu() {
        System.out.println(
                "――――― Welcome To The Edit Category Page ――――――\n " +
                        "✎ 1. Add Category\n " +
                        "✎ 2. Edit Category Detail\n " +
                        "✎ 4. Exit Menu");
    }

    public static void register(MainService mainService) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your national code: ");
        String nationalCode = scanner.next();
        scanner.nextLine();

        User user = new User(username, password, nationalCode);
        try {
            mainService.register(user);

            userMenu(mainService);
        } catch (RuntimeException runtimeException) {
            System.out.println("User with this username or national code has already been registered!, login please");
        }
    }

    public static void userMenu(MainService mainService) {
        boolean flag = true;

        while (flag) {
            printUserMenu();

            System.out.print("Choose an option: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    addProductToCartForRegister(mainService);
                    break;
                case 2:
                    showCartDetailForRegister(mainService);
                    break;
                case 3:
                    editCart(mainService);
                    break;
                case 4:
                    confirmationPurchasesForRegister(mainService);
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("You entered the wrong option!");
                    break;
            }
        }
    }

    public static void addProductToCartForRegister(MainService mainService) {
        if (mainService.showCartDetailForRegister().size() != 5) {
            boolean flag = true;

            while (flag) {
                mainService.showAllProduct();

                System.out.print("Please enter the desired product ID: ");
                int productId = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter the desired number of items (Pay attention to the stock): ");
                int productCount = scanner.nextInt();
                scanner.nextLine();

                Product product = mainService.showProductPrice(productId);
                int totalPrice = productCount * product.getPrice();

                try {
                    mainService.addProductToCartForRegister(productId, productCount, totalPrice);
                    System.out.println("add product was successfully");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }

                if (mainService.showCartDetailForRegister().size() == 5) {
                    System.out.println("You have added five products to the cart!");
                    flag = false;
                } else {
                    System.out.print("If you don't want to enter another item, enter 0 to exit and enter 1 to continue: ");
                    int exit = scanner.nextInt();
                    scanner.nextLine();

                    if (exit == 0) {
                        flag = false;
                    }
                }
            }
        } else {
            System.out.println("Your shopping cart is full!");
        }
    }

    public static void showCartDetailForRegister(MainService mainService) {
        mainService.showCartForRegister();
    }

    public static void editCart(MainService mainService) {
        boolean flag = true;

        while (flag) {

            printEditCartMenu();

            System.out.print("Choose an option: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    addProductToCartForRegister(mainService);
                    break;
                case 2:
                    deleteProductFromCartForRegister(mainService);
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("You entered the wrong option!");
                    break;
            }
        }
    }

    public static void deleteProductFromCartForRegister(MainService mainService) {
        mainService.showCartForRegister();

        System.out.print("Enter the Id of the product you want to delete: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        try {
            mainService.deleteProductFromCart(productId);
            System.out.println("Delete Product was done successfully");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void confirmationPurchasesForRegister(MainService mainService) {
        try {
            mainService.updateConfirmationForRegister();
            System.out.println("Your purchase has been successfully confirmed");

            mainService.updateProductStockForRegister();

            mainService.deleteCartForRegister();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void login(MainService mainService) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your national code: ");
        String nationalCode = scanner.next();
        scanner.nextLine();

        boolean flag = true;

        try {
            if (mainService.login(username, password, nationalCode)) {
                User user = mainService.findUser(username, nationalCode);
                if (user.getRole() == Role.valueOf("USER")) {
                    while (flag) {
                        printUserMenu();

                        System.out.print("Choose an option: ");
                        int input = scanner.nextInt();
                        scanner.nextLine();

                        switch (input) {
                            case 1:
                                addProductToCartForLogin(mainService);
                                break;
                            case 2:
                                showCartDetailForLogin(mainService);
                                break;
                            case 3:
                                editCartForLogin(mainService);
                                break;
                            case 4:
                                confirmationPurchasesForLogin(mainService);
                                break;
                            case 5:
                                flag = false;
                                break;
                            default:
                                System.out.println("You entered the wrong option!");
                                break;
                        }
                    }
                } else if (user.getRole() == Role.valueOf("ADMIN")) {
                    while (flag) {
                        printAdminMenu();

                        System.out.print("Choose an option: ");
                        int input = scanner.nextInt();
                        scanner.nextLine();

                        switch (input) {
                            case 1:
                                editProduct(mainService);
                                break;
                            case 2:
                                editCategory(mainService);
                                break;
                            case 3:
                                flag = false;
                                break;
                            default:
                                System.out.println("You entered the wrong option!");
                                break;
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println("User with this username or nationalCode was not found. Please register");
        }
    }

    public static void addProductToCartForLogin(MainService mainService) {
        if (mainService.showCartDetailForLogin().size() != 5) {
            boolean flag = true;

            try {
                while (flag) {
                    mainService.showAllProduct();

                    System.out.print("Please enter the desired product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the desired number of items (Pay attention to the stock): ");
                    int productCount = scanner.nextInt();
                    scanner.nextLine();

                    Product product = mainService.showProductPrice(productId);
                    int totalPrice = productCount * product.getPrice();

                    try {
                        mainService.addProductToCartForLogin(productId, productCount, totalPrice);
                        System.out.println("add product was successfully");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    if (mainService.showCartDetailForLogin().size() == 5) {
                        System.out.println("You have added five products to the cart!");
                        flag = false;
                    } else {
                        System.out.print("If you don't want to enter another item, enter 0 to exit and enter 1 to continue: ");
                        int exit = scanner.nextInt();
                        scanner.nextLine();

                        if (exit == 0) {
                            flag = false;
                        }
                    }
                }
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Your shopping cart is full!");
        }
    }

    public static void showCartDetailForLogin(MainService mainService) {
        mainService.showCartForLogin();
    }

    public static void editCartForLogin(MainService mainService) {
        boolean flag = true;

        while (flag) {

            printEditCartMenu();

            System.out.print("Choose an option: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    addProductToCartForLogin(mainService);
                    break;
                case 2:
                    deleteProductFromCartForLogin(mainService);
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("You entered the wrong option!");
                    break;
            }
        }
    }

    public static void deleteProductFromCartForLogin(MainService mainService) {
        mainService.showCartForLogin();

        System.out.print("Enter the Id of the product you want to delete: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        try {
            mainService.deleteProductFromCart(productId);
            System.out.println("Delete Product was done successfully");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void confirmationPurchasesForLogin(MainService mainService) {
        try {
            mainService.updateConfirmationForLogin();
            System.out.println("Your purchase has been successfully confirmed");

            mainService.updateProductStockForLogin();

            mainService.deleteCartForLogin();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editProduct(MainService mainService) {
        boolean flag = true;
        while (flag) {

            printEditProductMenu();

            System.out.print("Choose an option: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    addProduct(mainService);
                    break;
                case 2:
                    editProductDetail(mainService);
                    break;
                case 3:
                    deleteProduct(mainService);
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("You entered the wrong option!");
                    break;
            }
        }
    }

    public static void addProduct(MainService mainService) {
        try {
            System.out.print("Enter the product name: ");
            String name = scanner.nextLine();

            mainService.showAllCategory();

            System.out.print("Enter the category id: ");
            int categoryId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the product stock: ");
            int stock = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the product price: ");
            int price = scanner.nextInt();
            scanner.nextLine();

            Categories categories = new Categories(categoryId);
            Product product = new Product(name, categories, stock, price);
            try {
                if (mainService.addProduct(product)) {
                    System.out.println("create product was successfully");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void editProductDetail(MainService mainService) {
        try {
            mainService.showAllProduct();

            System.out.print("Enter the ID of the product you want to edit: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the product name: ");
            String name = scanner.nextLine();

            mainService.showAllCategory();

            System.out.print("Enter the category id: ");
            int categoryId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the product stock: ");
            int stock = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the product price: ");
            int price = scanner.nextInt();
            scanner.nextLine();

            Categories categories = new Categories(categoryId);
            Product product = new Product(id, name, categories, stock, price);

            try {
                mainService.editProductDetail(product);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteProduct(MainService mainService) {
        try {
            mainService.showAllProduct();

            System.out.print("Enter the ID of the product you want to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            try {
                mainService.deleteProduct(id);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void editCategory(MainService mainService) {
        boolean flag = true;

        while (flag) {

            printEditCategoryMenu();

            System.out.print("Choose an option: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    addCategory(mainService);
                    break;
                case 2:
                    editCategoryDetail(mainService);
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("You entered the wrong option!");
                    break;
            }
        }
    }

    public static void addCategory(MainService mainService) {
        try {
            System.out.print("Enter the category name: ");
            String category = scanner.next().toUpperCase();
            scanner.nextLine();

            System.out.print("Enter the sub category name: ");
            String subCategory = scanner.next().toUpperCase();
            scanner.nextLine();

            Categories categories = new Categories(category, subCategory);
            try {
                if (mainService.addCategory(categories)) {
                    System.out.println("add category was successfully");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void editCategoryDetail(MainService mainService) {
        try {
            mainService.showAllCategory();

            System.out.print("Enter the ID of the category you want to edit: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the category name: ");
            String category = scanner.next().toUpperCase();
            scanner.nextLine();

            System.out.print("Enter the sub category name: ");
            String subCategory = scanner.next().toUpperCase();
            scanner.nextLine();

            Categories categories = new Categories(id, category, subCategory);
            try {
                mainService.editCategory(categories);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
