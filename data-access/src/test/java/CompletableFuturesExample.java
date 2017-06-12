import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by rdas on 08/06/2017.
 * https://github.com/elqsar/completablefuture-examples
 * https://github.com/rob-signorelli/completablefuturedemos
 */
public class CompletableFuturesExample {
    public static List<Shop> shops = Arrays.asList(
            new Shop("Shop A"),
            new Shop("Shop B"),
            new Shop("Shop C"),
            new Shop("Shop D"),
            new Shop("Shop E"),
            new Shop("Shop F"),
            new Shop("Shop G"),
            new Shop("Shop J"),
            new Shop("Shop K")
    );


    public static void main(String[] args) {
        //stream->map->collect
        List<BigDecimal> collect = shops.parallelStream()
                .map(Shop::getPrice)
                .collect(toList());
        System.out.println(collect);

        //stream->completable Future->collect
        List<CompletableFuture<BigDecimal>> collect1 = shops.parallelStream()
                .map(aShop -> CompletableFuture.supplyAsync(aShop::getPrice))
                .collect(toList());
        //completable Future->join->collect
        List<BigDecimal> collect2 = collect1.stream()
                .map(CompletableFuture::join)
                .collect(toList());
        System.out.println(collect2);

        //stream->completable Future->join->collect
        List<BigDecimal> collect3 = shops.parallelStream()
                .map(aShop -> CompletableFuture.supplyAsync(aShop::getPrice))
                .map(CompletableFuture::join)
                .collect(toList());
        System.out.println(collect3);
    }

    ///class used in this example are below:

    @AllArgsConstructor
    @Data
    public static class Shop {
        private String name;

        public Future<BigDecimal> getPriceAsync() {
            CompletableFuture<BigDecimal> future = new CompletableFuture<>();
            //TODO
            new Thread(() -> {
                try {
                    BigDecimal price = calculatePrice();
                    future.complete(price);
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            }).start();


            return future;
        }

        public BigDecimal getPrice() {
            return calculatePrice();
        }

        public BigDecimal calculatePrice() {
            SlowNetwork.delay(1);
            // get the last Character of the shop
            char p = name.charAt(5);
            int p1 = (int) p;
            System.out.println("Calculating price of " + name + " with " + p);
            return BigDecimal.valueOf(p1);
            //return BigDecimal.valueOf(Randomizer.random(1000));
        }
    }


    public static class SlowNetwork {
        public static void delay(int i) {
            return;
        }
    }

    public static class Randomizer {
        public static long random(int i) {
            return 1L;
        }
    }

    Function<String , String > Upper = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return s.toUpperCase();
        }
    };

}
