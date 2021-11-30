package blockbuster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class blockbusterRunner {

    public static void main(String[] args) {
        Transaction transaction = new Transaction(false, false);
        Customer customer = new Customer();
        customer.setName("Floop Kartsenflogger");
        customer.setAge(21);

        List<Rental> Rentals = new ArrayList<>();
        populateMovieSelection();

        Rentals.add(new Rental(ProgramProperties.selection.get(0), ProgramProperties.TWO_DAYS_RENTED));
        Rentals.add(new Rental(ProgramProperties.selection.get(1), ProgramProperties.FOUR_DAYS_RENTED));
        Rentals.add(new Rental(ProgramProperties.selection.get(2), ProgramProperties.THREE_DAYS_RENTED));
        Rentals.add(new Rental(ProgramProperties.selection.get(3), ProgramProperties.ONE_DAY_RENTED));

        setRentalStrategies(Rentals);

        addCustomerRentals(customer, Rentals);

        List<String> uniqueGenre = Rentals.stream().map(Rental::getGenre).distinct().collect(Collectors.toCollection(ArrayList::new));
        List<Integer> daysRented = Rentals.stream().map(Rental::getDaysRented).collect(Collectors.toList());

        setTransaction(customer, uniqueGenre, daysRented, transaction);

        setFRPStrategies(Rentals);

        System.out.println(customer.htmlRentalReceipt(transaction));

    }

    public static void populateMovieSelection() {
        ProgramProperties.selection.add(new Movie("Jurassic Park", ProgramProperties.THRILLER_GENRE, ProgramProperties.TWO_YEARS));
        ProgramProperties.selection.add(new Movie("Empire Strikes Back", ProgramProperties.CHILDRENS_GENRE, ProgramProperties.THREE_WEEKS));
        ProgramProperties.selection.add(new Movie("Parasite", ProgramProperties.THRILLER_GENRE, ProgramProperties.ONE_WEEK));
        ProgramProperties.selection.add(new Movie("Cory in the House", ProgramProperties.CHILDRENS_GENRE, ProgramProperties.TWO_WEEKS));
    }

    public static void setRentalStrategies(List<Rental> rentals) {
        for(Rental r : rentals)
            if (r.getMovie().getReleaseDate() < ProgramProperties.TWO_WEEKS) r.setPriceStrategy(new NewPriceStrategy());
            else if (r.getMovie().getGenre().equalsIgnoreCase(ProgramProperties.CHILDRENS_GENRE))
                r.setPriceStrategy(new ChildrensPriceStrategy());
            else r.setPriceStrategy(new RegularPriceStrategy());
    }

    public static void setFRPStrategies(List<Rental> rentals) {
        for(Rental r : rentals) {
            if (r.getMovie().getReleaseDate() < ProgramProperties.TWO_WEEKS) {
                r.setFrequentRenterPointsStrategy(new BonusFrequentRenterPointsStrategy());
            } else {
                r.setFrequentRenterPointsStrategy(new FrequentRenterPointsStrategy());
            }
        }
    }

    public static void setTransaction(Customer customer, List<String> genres, List<Integer> daysRented, Transaction transaction) {
        int numberOfGenres = 0;
        int customerAge = customer.getAge();
        boolean doubleForAgeNew = false;
        boolean doubleForMultipleGenre = false;

        for(String g : genres) numberOfGenres++;

        // if customer rents any new movies while being 18-22 double points
        if(customerAge <= 22 && customerAge >= 18) for (Integer d : daysRented)
            if (d < ProgramProperties.TWO_WEEKS) { doubleForAgeNew = true; }

        // indicate double renter points
        if (numberOfGenres > 1) doubleForMultipleGenre = true;

        transaction.setNewMovie(doubleForAgeNew);
        transaction.setMultipleGenre(doubleForMultipleGenre);

    }

    public static void addCustomerRentals(Customer customer, List<Rental> rentals) {
        for(Rental r : rentals) customer.addRental(r);
    }
}
